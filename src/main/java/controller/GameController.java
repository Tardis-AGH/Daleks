package controller;

import javafx.event.Event;
import javafx.stage.Stage;

import model.board.Move;
import model.game.Game;
import view.GameWindow;

/**
 * The type Game controller.
 */
public class GameController {

    private Game game;
    private Stage primaryStage;
    private final GameWindow gameWindow;

    /**
     * Instantiates a new Game controller.
     *
     * @param game the game
     * @param primaryStage the primary stage
     */
    public GameController(Game game, Stage primaryStage) {
        this.game = game;
        this.primaryStage = primaryStage;
        this.gameWindow = new GameWindow(this);
    }

    /**
     * Handle right.
     *
     * @param event the event
     */
    public void handleRestart(Event event) {
        //TODO
    }

    /**
     * Next turn.
     *
     * @param move the move
     */
    public void nextTurn(Move move) {
        //TODO
        System.out.println(move);
        game.makeMoves(move);
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

    public GameWindow getGameWindow(){
        return this.gameWindow;
    }
}
