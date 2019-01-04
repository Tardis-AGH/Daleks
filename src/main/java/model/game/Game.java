package model.game;

import model.action.Action;
import model.board.Board;
import model.board.Move;
import model.board.coordinates.Coordinates;
import model.board.factory.BoardFactory;
import model.element.BoardElement;
import model.element.DynamicBoardElement;
import model.element.dynamicelement.Dalek;
import model.element.dynamicelement.Doctor;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Root class of the model.
 */
public class Game {

    private static final int DEFAULT_NUMBER_OF_LIVES = 3;
    private static final int DEFAULT_NUMBER_OF_TELEPORTERS = 3;
    private static final int DEFAULT_NUMBER_OF_BOMBS = 2;
    private static final int LEVEL_UP_POINTS = 5;
    private final BoardFactory boardFactory;
    private final GameState gameState;
    private Board board;

    /**
     * Instantiates a new Game.
     *
     * @param boardFactory the board generator
     */
    public Game(BoardFactory boardFactory) {
        this.boardFactory = boardFactory;
        this.board = boardFactory.generateNewBoard(1);
        this.gameState = new GameState(DEFAULT_NUMBER_OF_LIVES, DEFAULT_NUMBER_OF_TELEPORTERS, DEFAULT_NUMBER_OF_BOMBS, 0, 0, 1, 0);
    }

    /**
     * Sets up new level.
     */
    public void nextLevel() {
        final int newLevel = gameState.getLevel() + 1;
        board = boardFactory.generateNewBoard(newLevel);
        board.getDoctor().setImage(gameState.getDoctorDeaths());
        gameState.setLevel(newLevel);
        gameState.setCurrentScore(gameState.getCurrentScore() + LEVEL_UP_POINTS);
        gameState.setHighestScore(Math.max(gameState.getCurrentScore(), gameState.getHighestScore()));
        gameState.setNumberOfTeleporters(gameState.getNumberOfTeleporters() + 1);
        gameState.setNumberOfLives(gameState.getNumberOfLives() + 1);
        gameState.setNumberOfBombs(gameState.getNumberOfBombs() + 1);
    }

    /**
     * Restart level.
     */
    public void restartLevel() {
        board = boardFactory.generateNewBoard(gameState.getLevel());
        gameState.setDoctorDeaths(gameState.getDoctorDeaths() + 1);
        board.getDoctor().setImage(gameState.getDoctorDeaths());
    }

    private Status makeDoctorsMove(Move move) {
        final Doctor doctor = board.getDoctor();
        final List<Action> actions = doctor.makeMove(move);
        return executeActions(actions);
    }

    private Status makeDaleksMoves(Map<Coordinates, BoardElement> collisionMap) {
        final Doctor doctor = board.getDoctor();
        for (Dalek dalek : board.getDaleks()) {
            dalek.makeMove(doctor.getCoordinates());
            final Status actionStatus = processCollision(collisionMap, dalek);
            if (actionStatus != Status.CONTINUE_GAME) {
                return actionStatus;
            }
        }
        return Status.CONTINUE_GAME;
    }

    /**
     * Make moves status.
     *
     * @param move the move
     *
     * @return the status
     */
    public Status makeMoves(Move move) {

        Status actionStatus = makeDoctorsMove(move);
        if (actionStatus != Status.CONTINUE_GAME) {
            return actionStatus == Status.SKIP_MOVE ? Status.CONTINUE_GAME : actionStatus;
        }

        final Map<Coordinates, BoardElement> collisionMap = board.getStaticBoardElements()
                .stream()
                .collect(Collectors.toMap(BoardElement::getCoordinates, Function.identity()));

        actionStatus = processCollision(collisionMap, board.getDoctor());
        if (actionStatus != Status.CONTINUE_GAME) {
            return actionStatus;
        }


        return makeDaleksMoves(collisionMap);
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
        return boardFactory.getBoardHeight();
    }

    /**
     * Gets board width.
     *
     * @return the board width
     */
    public int getBoardWidth() {
        return boardFactory.getBoardWidth();
    }
}
