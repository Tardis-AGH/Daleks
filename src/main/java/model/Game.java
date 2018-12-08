package model;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.action.Action;
import model.element.BoardElement;
import model.element.dynamicelement.Dalek;

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
        final Map<Coordinates, BoardElement> oldMap = board.getElements();
        board.setElements(new HashMap<>());

        // put all static elements into the new map
        for (BoardElement element : board.getStaticBoardElements(oldMap.values())) {
            board.getElements().put(element.getCoordinates(), element);
        }

        // Doctor's move
        executeActions(board.getDoctor().makeMove(move, oldMap));
        if (board.getElements().containsKey(board.getDoctor().getCoordinates())) {
            final Status actionsStatus = executeActions(
                    board.getElements().get(board.getDoctor().getCoordinates()).accept(board.getDoctor()));

            if (actionsStatus != Status.CONTINUE_GAME) {
                return actionsStatus;
            }
        } else {
            board.getElements().put(board.getDoctor().getCoordinates(), board.getDoctor());
        }

        // Daleks' moves
        for (Dalek dalek : board.getDaleks(oldMap.values())) {
            dalek.makeMove(board.getDoctor().getCoordinates());
            if (board.getElements().containsKey(dalek.getCoordinates())) {
                final Status actionsStatus =
                        executeActions(board.getElements().get(dalek.getCoordinates()).accept(dalek));

                if (actionsStatus != Status.CONTINUE_GAME) {
                    return actionsStatus;
                }
            } else {
                board.getElements().put(dalek.getCoordinates(), dalek);
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
