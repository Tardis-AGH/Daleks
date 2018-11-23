package model;

import java.util.List;

public class Game {
    private GameState gameState;
    private Board board;

    public Status makeMoves(Move move) {
        //TODO
        return null;
    }

    public Status executeActions(List<Action> actionList) {
        //TODO
        return null;
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
