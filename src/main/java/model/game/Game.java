package model.game;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.action.Action;
import model.board.Board;
import model.board.Coordinates;
import model.board.Move;
import model.board.generator.BoardGenerator;
import model.board.generator.RandomBoardGenerator;
import model.element.BoardElement;
import model.element.DynamicBoardElement;
import model.element.dynamicelement.Dalek;
import model.element.dynamicelement.Doctor;

/**
 * Root class of the model.
 */
public class Game {

    private static final int DEFAULT_NUMBER_OF_LIVES = 3;
    private static final int DEFAULT_NUMBER_OF_TELEPORTERS = 3;
    private static final int LEVEL_UP_POINTS = 5;
    private final BoardGenerator boardGenerator;
    private GameState gameState;
    private Board board;

    /**
     * Instantiates a new Game.
     *
     * @param boardGenerator the board generator
     */
    public Game(BoardGenerator boardGenerator) {
        this.boardGenerator = boardGenerator;
        this.board = boardGenerator.generateNewBoard(1);
        this.gameState =
                new GameState(DEFAULT_NUMBER_OF_LIVES, DEFAULT_NUMBER_OF_TELEPORTERS, 0, 0, board.getDaleks().size());
    }

    /**
     * Sets up new level.
     *
     * @return the status
     */
    public void nextLevel() {
        int newLevel = gameState.getLevel() + 1;
        board = boardGenerator.generateNewBoard(newLevel);
        gameState.setLevel(newLevel);
        gameState.setCurrentScore(gameState.getCurrentScore() + LEVEL_UP_POINTS);
        gameState.setHighestScore(Math.max(gameState.getCurrentScore(), gameState.getHighestScore()));
        gameState.setNumberOfTeleporters(gameState.getNumberOfTeleporters() + 1);
        gameState.setNumberOfLives(gameState.getNumberOfLives() + 1);
    }

    /**
     * Restart level.
     */
    public void restartLevel() {
        board = boardGenerator.generateNewBoard(gameState.getLevel());
    }

    /**
     * Make moves status.
     *
     * @param move the move
     *
     * @return the status
     */
    public Status makeMoves(Move move) {

        // swap maps
        final Map<Coordinates, BoardElement> collisionMap = new HashMap<>();

        // put all static elements into the new map
        for (BoardElement element : board.getStaticBoardElements()) {
            collisionMap.put(element.getCoordinates(), element);
        }

        // Doctor's move
        final Doctor doctor = board.getDoctor();
        Status actionStatus = executeActions(doctor.makeMove(move));
        if (actionStatus == Status.SKIP_MOVE) {
            return Status.CONTINUE_GAME;
        }
        actionStatus = processCollision(collisionMap, doctor);
        if (actionStatus != Status.CONTINUE_GAME) {
            return actionStatus;
        }

        // Daleks' moves
        for (Dalek dalek : board.getDaleks()) {
            dalek.makeMove(doctor.getCoordinates());
            actionStatus = processCollision(collisionMap, dalek);
            if (actionStatus != Status.CONTINUE_GAME) {
                return actionStatus;
            }
        }
        return Status.CONTINUE_GAME;
    }

    private Status executeActions(List<Action> actionList) {

        return actionList.stream()
                .map(a -> a.execute(this))
                .max(Comparator.comparing(Status::ordinal))
                .orElse(Status.CONTINUE_GAME);
    }

    private Status processCollision(Map<Coordinates, BoardElement> collisionMap, DynamicBoardElement visitor) {
        if (collisionMap.containsKey(visitor.getCoordinates())) {
            final BoardElement visited = collisionMap.get(visitor.getCoordinates());
            final InteractionResult interactionResult = visited.accept(visitor);
            final BoardElement fieldWinner = interactionResult.getFieldWinner();
            collisionMap.put(fieldWinner.getCoordinates(), fieldWinner);
            final Status actionsStatus = executeActions(interactionResult.getActionsToExecute());
            if (actionsStatus != Status.CONTINUE_GAME) {
                return actionsStatus;
            }
        } else {
            collisionMap.put(visitor.getCoordinates(), visitor);
        }
        return Status.CONTINUE_GAME;
    }

    /**
     * Gets game state.
     *
     * @return the game state
     */
    public GameState getGameState() {
        return gameState;
    }

    /**
     * Gets board.
     *
     * @return the board
     */
    public Board getBoard() {
        return board;
    }

    /**
     * Sets board.
     *
     * @param board the board
     */
    public void setBoard(Board board) {
        this.board = board;
    }

    /**
     * Gets board height.
     *
     * @return the board height
     */
    public int getBoardHeight() {
        return boardGenerator.getBoardHeight();
    }

    /**
     * Gets board width.
     *
     * @return the board width
     */
    public int getBoardWidth() {
        return boardGenerator.getBoardWidth();
    }
}
