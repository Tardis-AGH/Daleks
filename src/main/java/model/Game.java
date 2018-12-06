package model;

import model.action.Action;
import model.element.BoardElement;
import model.element.dynamicelement.Dalek;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        executeActions(board.getDoctor().makeMove(move, board.getElements()));
        if (collisionMap.containsKey(board.getDoctor().getCoordinates())) {
            final Status actionsStatus = executeActions(
                    collisionMap.get(board.getDoctor().getCoordinates()).accept(board.getDoctor()));
            //TODO hashmap update

            if (actionsStatus != Status.CONTINUE_GAME) {
                return actionsStatus;
            }
        } else {
            collisionMap.put(board.getDoctor().getCoordinates(), board.getDoctor());
        }

        // Daleks' moves
        for (Dalek dalek : board.getDaleks()) {
            dalek.makeMove(board.getDoctor().getCoordinates());
            if (collisionMap.containsKey(dalek.getCoordinates())) {
                final Status actionsStatus =
                        executeActions(collisionMap.get(dalek.getCoordinates()).accept(dalek));
                //TODO hashmap update

                if (actionsStatus != Status.CONTINUE_GAME) {
                    return actionsStatus;
                }
            } else {
                collisionMap.put(dalek.getCoordinates(), dalek);
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
