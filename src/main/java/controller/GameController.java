package controller;

import javafx.event.Event;
import javafx.stage.Stage;
import model.board.Move;
import model.game.Game;
import model.game.Status;
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
     * @param primaryStage the primary stage
     */
    public GameController(Stage primaryStage) {
        this.primaryStage = primaryStage;

        game = new Game();
        gameWindow = new GameWindow(this);

        gameWindow.initSprites(game.getBoard().getElements(), game.getBoardWidth());
    }

    /**
     * Handle right.
     *
     * @param event the event
     */
    public void handleRestart(Event event) {
        gameWindow.unfreezeGame();

        game = new Game();
        gameWindow.initSprites(game.getBoard().getElements(), game.getBoardWidth());
    }

    /**
     * Next turn.
     *
     * @param move the move
     */
    public void nextTurn(Move move) {
        Status gameStatus = game.makeMoves(move);

        switch (gameStatus) {
            case CONTINUE_GAME:
                break;
            case LEVEL_UP:
                game.nextLevel();
                gameWindow.initSprites(game.getBoard().getElements(), game.getBoardWidth());
                break;
            case RESTART_GAME:
                game.restartLevel();
                gameWindow.initSprites(game.getBoard().getElements(), game.getBoardWidth());
                break;
            case GAME_OVER:
                gameWindow.freezeGame();
                break;
        }
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
