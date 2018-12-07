package model;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.action.Action;
import model.element.BoardElement;
import model.element.DynamicBoardElement;
import model.element.dynamicelement.Dalek;
import model.element.dynamicelement.Doctor;

/**
 * Root class of the model.
 */
public class Game {

    private GameState gameState;
    private Board board;

    /**
     * Instantiates a new Game.
     *
     * @param gameState the game state
     * @param board the board
     */
    public Game(GameState gameState, Board board) {
        this.gameState = gameState;
        this.board = board;
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
        executeActions(doctor.makeMove(move, board.getElements()));
        processCollision(collisionMap, doctor);

        // Daleks' moves
        for (Dalek dalek : board.getDaleks()) {
            dalek.makeMove(doctor.getCoordinates());
            final Status actionStatus = processCollision(collisionMap, dalek);
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
}
