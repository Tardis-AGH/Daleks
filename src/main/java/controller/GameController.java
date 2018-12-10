package controller;

import javafx.event.Event;
import javafx.stage.Stage;
import model.Game;
import model.board.Move;

/**
 * The type Game controller.
 */
public class GameController {

    private Game game;
    private Stage primaryStage;

    /**
     * Instantiates a new Game controller.
     *
     * @param game the game
     * @param primaryStage the primary stage
     */
    public GameController(Game game, Stage primaryStage) {
        this.game = game;
        this.primaryStage = primaryStage;
    }

    /**
     * Handle left.
     *
     * @param event the event
     */
    public void handleLeft(Event event) {
        //TODO
    }

    /**
     * Handle right.
     *
     * @param event the event
     */
    public void handleRight(Event event) {
        //TODO
    }

    /**
     * Next turn.
     *
     * @param move the move
     */
    public void nextTurn(Move move) {
        //TODO
    }

    /**
     * Gets primary stage.
     *
     * @return the primary stage
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }

    /**
     * Sets primary stage.
     *
     * @param primaryStage the primary stage
     */
    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    /**
     * Gets game.
     *
     * @return the game
     */
    public Game getGame() {
        return game;
    }

    /**
     * Sets game.
     *
     * @param game the game
     */
    public void setGame(Game game) {
        this.game = game;
    }
}
