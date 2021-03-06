package controller;

import java.util.stream.Collectors;
import javafx.beans.value.ObservableValue;
import javafx.collections.SetChangeListener;
import javafx.event.Event;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.board.Move;
import model.board.factory.RandomBoardFactory;
import model.element.BoardElement;
import model.game.Game;
import model.game.GameState;
import model.game.Status;
import view.GameWindow;

/**
 * The type Game controller.
 */
public class GameController {

    private final GameWindow gameWindow;
    private Game game;
    private Stage primaryStage;
    private HighScoreManager highScoreManager;

    /**
     * Instantiates a new Game controller.
     *
     * @param primaryStage the primary stage
     */
    public GameController(Stage primaryStage) {
        this.primaryStage = primaryStage;
        game = new Game(new RandomBoardFactory());
        gameWindow = new GameWindow(this);
        highScoreManager = new HighScoreManager("score");
        initSprites();
        initGameStateProperties();
    }

    /**
     * Init sprites.
     */
    private void initSprites() {
        removeSprites();

        for (BoardElement boardElement : game.getBoard().getElements()) {
            final ImageView spriteImage =
                    gameWindow.createSpriteImage(boardElement.getImagePath(), game.getBoardWidth());
            new Sprite(boardElement, spriteImage, gameWindow.getTiles());
        }

        game.getBoard().getElements().addListener((SetChangeListener.Change<? extends BoardElement> change) -> {
            if (change.wasAdded()) {
                final ImageView spriteImage =
                        gameWindow.createSpriteImage(change.getElementAdded().getImagePath(), game.getBoardWidth());
                new Sprite(change.getElementAdded(), spriteImage, gameWindow.getTiles());
            }
        });
    }

    private void removeSprites() {
        final GridPane tiles = gameWindow.getTiles();
        tiles.getChildren()
                .removeAll(
                        tiles.getChildren().stream().filter(c -> c instanceof ImageView).collect(Collectors.toList()));
    }

    /**
     * Init game state properties.
     */
    private void initGameStateProperties() {
        final GameState gameState = game.getGameState();

        gameWindow.getNumberOfLivesLabel().textProperty().bind(gameState.numberOfLivesProperty().asString());
        gameWindow.getNumberOfTeleportersLabel()
                .textProperty()
                .bind(gameState.numberOfTeleportersProperty().asString());
        gameWindow.getNumberOfBombsLabel().textProperty().bind(gameState.numberOfBombsProperty().asString());
        gameWindow.getScoreLabel().textProperty().bind(gameState.currentScoreProperty().asString());
        gameWindow.getHighScoreLabel().textProperty().bind(gameState.highestScoreProperty().asString());
        gameWindow.getLevelLabel().textProperty().bind(gameState.levelProperty().asString());

        gameState.numberOfTeleportersProperty()
                .addListener((ObservableValue<? extends Number> observableValue, Number number, Number t1) -> {
                    if (t1.intValue() == 0) {
                        gameWindow.getTeleporterButton().setDisable(true);
                    }
                    if (number.intValue() == 0) {
                        gameWindow.getTeleporterButton().setDisable(false);
                    }
                });

        gameState.numberOfBombsProperty()
                .addListener((ObservableValue<? extends Number> observableValue, Number number, Number t1) -> {
                    if (t1.intValue() == 0) {
                        gameWindow.getBombButton().setDisable(true);
                    }
                    if (number.intValue() == 0) {
                        gameWindow.getBombButton().setDisable(false);
                    }
                });

        gameState.numberOfLivesProperty()
                .addListener((ObservableValue<? extends Number> observableValue, Number number, Number t1) -> {
                    if (t1.intValue() < 0) {
                        gameWindow.getNumberOfLivesLabel().textProperty().unbind();
                        gameWindow.getNumberOfLivesLabel().setText("☠");

                    }
                });

        gameState.setHighestScore(highScoreManager.getHighScore());
    }

    /**
     * Next turn.
     *
     * @param move the move
     */
    public void nextTurn(Move move) {
        final Status gameStatus = game.makeMoves(move);

        switch (gameStatus) {
            case CONTINUE_GAME:
                break;
            case LEVEL_UP:
                game.nextLevel();
                initSprites();
                break;
            case RESTART_LEVEL:
                game.restartLevel();
                initSprites();
                break;
            case GAME_OVER:
                removeSprites();
                setGameControlsDisable(true);
                break;
        }
    }

    private void setGameControlsDisable(Boolean f) {
        gameWindow.getDownButton().setDisable(f);
        gameWindow.getLowerLeftButton().setDisable(f);
        gameWindow.getLeftButton().setDisable(f);
        gameWindow.getUpperLeftButton().setDisable(f);
        gameWindow.getUpButton().setDisable(f);
        gameWindow.getUpperRightButton().setDisable(f);
        gameWindow.getRightButton().setDisable(f);
        gameWindow.getLowerRightButton().setDisable(f);
        gameWindow.getWaitButton().setDisable(f);
        gameWindow.getTeleporterButton().setDisable(f);
        gameWindow.getBombButton().setDisable(f);
    }

    /**
     * Handle restart.
     *
     * @param event the event
     */
    public void handleRestart(Event event) {
        final int currentGameScore = game.getGameState().getHighestScore();
        if (currentGameScore > highScoreManager.getHighScore()) {
            highScoreManager.setHighScore(currentGameScore);
        }
        game = new Game(new RandomBoardFactory());
        game.getGameState().setHighestScore(highScoreManager.getHighScore());

        setGameControlsDisable(false);
        initSprites();
        initGameStateProperties();
    }

    /**
     * Gets primary stage.
     *
     * @return the primary stage
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

    /**
     * Gets game window.
     *
     * @return the game window
     */
    public GameWindow getGameWindow() {
        return this.gameWindow;
    }
}
