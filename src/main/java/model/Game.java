package model;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Root class of the model
 */

public class Game {
    private GameState gameState;
    private Board board;

    public Game(GameState gameState, Board board) {
        this.gameState = gameState;
        this.board = board;
    }

    public Status makeMoves(Move move) {

//        maps swap
        Map<Coordinates, BoardElement> oldMap = board.getElements();
        board.setElements(new HashMap<>());

//        all static elements are put into the new map
        for (BoardElement element : board.getStaticBoardElements(oldMap.values()))
            board.getElements().put(element.getCoordinates(), element);

//        Doctor's move
        executeActions(board.getDoctor().makeMove(move, oldMap));
        if (board.getElements().containsKey(board.getDoctor().getCoordinates())) {
            Status actionsStatus = executeActions(
                    board.getElements()
                            .get(board.getDoctor().getCoordinates())
                            .accept(board.getDoctor()));

            if (actionsStatus != Status.CONTINUE_GAME) return actionsStatus;
        } else board.getElements().put(board.getDoctor().getCoordinates(), board.getDoctor());

//        Daleks' moves
        for (Dalek dalek : board.getDaleks(oldMap.values())) {
            dalek.makeMove(board.getDoctor().getCoordinates());
            if (board.getElements().containsKey(dalek.getCoordinates())) {
                Status actionsStatus = executeActions(
                        board.getElements()
                                .get(dalek.getCoordinates())
                                .accept(dalek));

                if (actionsStatus != Status.CONTINUE_GAME) return actionsStatus;
            } else board.getElements().put(dalek.getCoordinates(), dalek);
        }

        return Status.CONTINUE_GAME;
    }

    private Status executeActions(List<Action> actionList) {

        return actionList
                .stream()
                .map(a -> a.execute(this))
                .max(Comparator.comparing(Status::ordinal))
                .orElse(Status.CONTINUE_GAME);
    }

    public GameState getGameState() {
        return gameState;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }
}
