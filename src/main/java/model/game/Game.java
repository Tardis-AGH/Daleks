package model.game;

import model.action.Action;
import model.board.Board;
import model.board.Coordinates;
import model.board.Move;
import model.board.generator.BoardGenerator;
import model.element.BoardElement;
import model.element.DynamicBoardElement;
import model.element.dynamicelement.Dalek;
import model.element.dynamicelement.Doctor;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Root class of the model.
 */
public class Game {

    private final GameState gameState;
    private Board board;

    private BoardGenerator boardGenerator;

    /**
     * Instantiates a new Game.
     */
    public Game() {
        this.boardGenerator = new BoardGenerator();
        this.board = boardGenerator.generateNewBoard(1);

        this.gameState = new GameState(3, 3, 0, 1, 1, board.getDaleks().size());
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
        gameState.setCurrentScore(gameState.getCurrentScore() + 5);
        gameState.setHighestScore(Math.max(gameState.getCurrentScore(), gameState.getHighestScore()));
        gameState.setNumberOfTeleporters(gameState.getNumberOfTeleporters() + 1);
        gameState.setNumberOfLives(gameState.getNumberOfLives() + 1);
        gameState.setEnemyCount(board.getDaleks().size());

    }

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
        executeActions(doctor.makeMove(move));
        Status actionStatus = processCollision(collisionMap, doctor);
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


    public int getBoardHeight() {
        return boardGenerator.getBoardHeight();
    }

    public int getBoardWidth() {
        return boardGenerator.getBoardWidth();
    }
}