package controller;

import javafx.event.Event;
import javafx.stage.Stage;
import model.Game;
import model.Move;

public class GameController {
    public GameController(Game game, Stage primaryStage) {
        this.game = game;
        this.primaryStage = primaryStage;
    }

    private Game game;
    private Stage primaryStage;

    public void handleLeft(Event event) {
        //TODO
    }

    public void handleRight(Event event) {
        //TODO
    }

    public void nextTurn(Move move) {
        //TODO
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }
}
