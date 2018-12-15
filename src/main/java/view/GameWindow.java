package view;

import controller.GameController;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableSet;
import javafx.collections.SetChangeListener;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import model.board.Move;
import model.element.BoardElement;
import model.game.GameState;

import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * The type Game window.
 */
public class GameWindow extends VBox {

    //window resolution
    private static final int NATIVE_BOARD_WIDTH = 600;
    private static final int NATIVE_BOARD_HEIGHT = 800;

    private static final int NAVIGATION_BUTTON_SIZE = 60;

    private GridPane tiles;

    private Button down;
    private Button lowerLeft;
    private Button left;
    private Button upperLeft;
    private Button up;
    private Button upperRight;
    private Button right;
    private Button lowerRight;
    private Button wait;
    private Button teleporter;

    private Label numberOfLivesLabel;
    private Label numberOfTeleportersLabel;
    private Label scoreLabel;
    private Label highScoreLabel;
    private Label levelLabel;

    /**
     * Instantiates a new Game window.
     *
     * @param gameController the game controller
     */
    public GameWindow(GameController gameController) {
        setPrefSize(NATIVE_BOARD_WIDTH, NATIVE_BOARD_HEIGHT);

        tiles = createTiles(gameController);
        getChildren().add(tiles);

        BorderPane controls = createControls(gameController);
        getChildren().add(controls);
    }

    private GridPane createTiles(GameController gameController) {
        GridPane tiles = new GridPane();
        tiles.setGridLinesVisible(true);
        int prefferedTileSize = NATIVE_BOARD_WIDTH / gameController.getGame().getBoardWidth();
        Stream<ColumnConstraints> columns = Stream.generate(ColumnConstraints::new)
                .peek(e -> e.setPrefWidth(prefferedTileSize))
                .limit(gameController.getGame().getBoardWidth());

        Stream<RowConstraints> rows = Stream.generate(RowConstraints::new)
                .peek(e -> e.setPrefHeight(prefferedTileSize))
                .limit(gameController.getGame().getBoardHeight());
        tiles.getColumnConstraints().addAll(columns.collect(Collectors.toSet()));
        tiles.getRowConstraints().addAll(rows.collect(Collectors.toSet()));

        return tiles;
    }

    private BorderPane createControls(GameController gameController) {
        BorderPane lowerBar = new BorderPane();
        VBox metrics = createMetrics();
        GridPane movementButtons = createMovementButtons(gameController);
        VBox specialButtons = createSpecialButtons(gameController);

        lowerBar.setLeft(metrics);
        lowerBar.setCenter(movementButtons);
        movementButtons.setTranslateX((double) NATIVE_BOARD_WIDTH * 0.05);
        movementButtons.setTranslateY(25);
        lowerBar.setRight(specialButtons);
        lowerBar.setTop(new Region());

        return lowerBar;
    }

    private VBox createMetrics() {
        VBox metrics = new VBox(10);
        metrics.setPadding(new Insets(50, 30, 30, 30));

        levelLabel = new Label();
        numberOfLivesLabel = new Label();
        numberOfTeleportersLabel = new Label();
        scoreLabel = new Label();
        highScoreLabel = new Label();

        metrics.getChildren().add(createLabelHolder(levelLabel, "Level:"));
        metrics.getChildren().add(createLabelHolder(numberOfLivesLabel, "Number of lives:"));
        metrics.getChildren().add(createLabelHolder(numberOfTeleportersLabel, "Number of teleporters:"));
        metrics.getChildren().add(createLabelHolder(scoreLabel, "Score:"));
        metrics.getChildren().add(createLabelHolder(highScoreLabel, "Highest score:"));

        return metrics;
    }

    private BorderPane createLabelHolder(Label numericLabel, String labelText) {
        BorderPane labelHolder = new BorderPane();
        labelHolder.setLeft(new Label(labelText));
        labelHolder.setRight(numericLabel);
        numericLabel.setPadding(new Insets(0, 0, 0, 5));
        return labelHolder;
    }

    private GridPane createMovementButtons(GameController gameController) {
        GridPane controls = new GridPane();

        up = createMovementButton("⬆", gameController, Move.UP);
        upperRight = createMovementButton("⬈", gameController, Move.UPPER_RIGHT);
        right = createMovementButton("➡", gameController, Move.RIGHT);
        lowerRight = createMovementButton("⬊", gameController, Move.LOWER_RIGHT);
        down = createMovementButton("⬇", gameController, Move.DOWN);
        lowerLeft = createMovementButton("⬋", gameController, Move.LOWER_LEFT);
        left = createMovementButton("⬅", gameController, Move.LEFT);
        upperLeft = createMovementButton("⬉", gameController, Move.UPPER_LEFT);
        wait = createMovementButton("●", gameController, Move.WAIT);

        controls.add(down, 1, 2);
        controls.add(left, 0, 1);
        controls.add(right, 2, 1);
        controls.add(up, 1, 0);
        controls.add(lowerLeft, 0, 2);
        controls.add(lowerRight, 2, 2);
        controls.add(upperLeft, 0, 0);
        controls.add(upperRight, 2, 0);
        controls.add(wait, 1, 1);

        return controls;
    }

    private Button createMovementButton(String buttonText, GameController gameController, Move move) {
        Button button = new Button();
        button.setPrefWidth(NAVIGATION_BUTTON_SIZE);
        button.setPrefHeight(NAVIGATION_BUTTON_SIZE);
        button.setOnAction(event -> gameController.nextTurn(move));
        button.setText(buttonText);
        button.setStyle("-fx-font: 20 calibri;");

        return button;
    }

    private VBox createSpecialButtons(GameController gameController) {
        VBox specialButtons = new VBox();

        teleporter = new Button();
        teleporter.setPrefWidth(1.5 * NAVIGATION_BUTTON_SIZE);
        teleporter.setPrefHeight(1.5 * NAVIGATION_BUTTON_SIZE);
        teleporter.setOnAction(event -> gameController.nextTurn(Move.TELEPORT));
        teleporter.setText("TELEPORT");

        Button restart = new Button();
        restart.setPrefWidth(1.5 * NAVIGATION_BUTTON_SIZE);
        restart.setPrefHeight(NAVIGATION_BUTTON_SIZE);
        restart.setOnAction(gameController::handleRestart);
        restart.setText("RESET");

        specialButtons.getChildren().add(teleporter);
        specialButtons.getChildren().add(restart);
        specialButtons.setPadding(new Insets(30, 30, 30, 30));
        specialButtons.setSpacing(30);

        return specialButtons;
    }

    /**
     * Gets native board width.
     *
     * @return the native board width
     */
    public static int getNativeBoardWidth() {
        return NATIVE_BOARD_WIDTH;
    }

    /**
     * Gets native board height.
     *
     * @return the native board height
     */
    public static int getNativeBoardHeight() {
        return NATIVE_BOARD_HEIGHT;
    }

    /**
     * Init sprites.
     *
     * @param elements the elements
     * @param boardWidth the board width
     */
    public void initSprites(ObservableSet<BoardElement> elements, int boardWidth) {
        removeSprites();

        for (BoardElement boardElement : elements) {
            createSprite(boardElement, boardWidth);
        }

        elements.addListener((SetChangeListener.Change<? extends BoardElement> change) -> {
            if (change.wasAdded()) {
                createSprite(change.getElementAdded(), boardWidth);
            }
        });
    }

    private void createSprite(BoardElement boardElement, int boardWidth) {
        Image image = new javafx.scene.image.Image(
                Objects.requireNonNull(getClass().getClassLoader().getResource(boardElement.getImagePath()))
                        .toExternalForm());
        new Sprite(boardElement, image, tiles, boardWidth);
    }

    private void removeSprites() {
        tiles.getChildren()
                .removeAll(tiles.getChildren().stream().filter(c -> c instanceof Sprite).collect(Collectors.toList()));
    }

    /**
     * Init game state properties.
     *
     * @param gameState the game state
     */
    public void initGameStateProperties(GameState gameState) {
        numberOfLivesLabel.textProperty().bind(gameState.numberOfLivesProperty().asString());
        numberOfTeleportersLabel.textProperty().bind(gameState.numberOfTeleportersProperty().asString());
        scoreLabel.textProperty().bind(gameState.currentScoreProperty().asString());
        highScoreLabel.textProperty().bind(gameState.highestScoreProperty().asString());
        levelLabel.textProperty().bind(gameState.levelProperty().asString());

        gameState.numberOfTeleportersProperty()
                .addListener((ObservableValue<? extends Number> observableValue, Number number, Number t1) -> {
                    if (t1.intValue() == 0) {
                        teleporter.setDisable(true);
                    }
                    if (number.intValue() == 0) {
                        teleporter.setDisable(false);
                    }
                });

        gameState.numberOfLivesProperty()
                .addListener((ObservableValue<? extends Number> observableValue, Number number, Number t1) -> {
                    if (t1.intValue() < 0) {
                        numberOfLivesLabel.textProperty().unbind();
                        numberOfLivesLabel.setText("☠");
                    }
                });
    }

    /**
     * Freeze game.
     */
    public void freezeGame() {
        removeSprites();

        setGameControlsDisable(true);
    }

    private void setGameControlsDisable(Boolean f) {
        down.setDisable(f);
        lowerLeft.setDisable(f);
        left.setDisable(f);
        upperLeft.setDisable(f);
        up.setDisable(f);
        upperRight.setDisable(f);
        right.setDisable(f);
        lowerRight.setDisable(f);
        wait.setDisable(f);
        teleporter.setDisable(f);
    }

    /**
     * Unfreeze game.
     */
    public void unfreezeGame() {
        setGameControlsDisable(false);
    }
}
