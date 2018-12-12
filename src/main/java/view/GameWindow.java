package view;

import controller.GameController;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableSet;
import javafx.collections.SetChangeListener;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import model.board.Move;
import model.element.BoardElement;
import model.game.GameState;

/**
 * The type Game window.
 */
public class GameWindow extends VBox {

    //presenter window resolution
    private static final int NATIVE_BOARD_WIDTH = 600;
    private static final int NATIVE_BOARD_HEIGHT = 800;

    private static final int NAVIGATION_BUTTON_SIZE = 60;

    private final Button down = new Button();
    private final Button lowerLeft = new Button();
    private final Button left = new Button();
    private final Button upperLeft = new Button();
    private final Button up = new Button();
    private final Button upperRight = new Button();
    private final Button right = new Button();
    private final Button lowerRight = new Button();
    private final Button wait = new Button();
    private final Button restart = new Button();
    private final Button teleport = new Button();

    private final Label numberOfLivesLabel = new Label();
    private final Label numberOfTeleportersLabel = new Label();
    private final Label scoreLabel = new Label();
    private final Label highScoreLabel = new Label();
    private final Label levelLabel = new Label();

    private final GridPane tiles;

    /**
     * Instantiates a new Game window.
     *
     * @param gameController the game controller
     */
    public GameWindow(GameController gameController) {
        this.setPrefSize(NATIVE_BOARD_WIDTH, NATIVE_BOARD_HEIGHT);

        GridPane gridPane = new GridPane();
        gridPane.setGridLinesVisible(true);
        int prefferedTileSize = NATIVE_BOARD_WIDTH / gameController.getGame().getBoardWidth();
        Stream<ColumnConstraints> columns = Stream.generate(ColumnConstraints::new)
                .peek(e -> e.setPrefWidth(prefferedTileSize))
                .limit(gameController.getGame().getBoardWidth());

        Stream<RowConstraints> rows = Stream.generate(RowConstraints::new)
                .peek(e -> e.setPrefHeight(prefferedTileSize))
                .limit(gameController.getGame().getBoardHeight());
        gridPane.getColumnConstraints().addAll(columns.collect(Collectors.toSet()));
        gridPane.getRowConstraints().addAll(rows.collect(Collectors.toSet()));
        this.tiles = gridPane;
        this.getChildren().add(this.tiles);

        GridPane controls = new GridPane();

        down.setPrefWidth(NAVIGATION_BUTTON_SIZE);
        down.setPrefHeight(NAVIGATION_BUTTON_SIZE);
        down.setOnAction(event -> gameController.nextTurn(Move.DOWN));
        down.setText("⬇");
        down.setStyle("-fx-font: 20 calibri;");

        lowerLeft.setPrefWidth(NAVIGATION_BUTTON_SIZE);
        lowerLeft.setPrefHeight(NAVIGATION_BUTTON_SIZE);
        lowerLeft.setOnAction(event -> gameController.nextTurn(Move.LOWER_LEFT));
        lowerLeft.setText("⬋");
        lowerLeft.setStyle("-fx-font: 20 calibri;");

        left.setPrefWidth(NAVIGATION_BUTTON_SIZE);
        left.setPrefHeight(NAVIGATION_BUTTON_SIZE);
        left.setOnAction(event -> gameController.nextTurn(Move.LEFT));
        left.setText("⬅");
        left.setStyle("-fx-font: 20 calibri;");

        upperLeft.setPrefWidth(NAVIGATION_BUTTON_SIZE);
        upperLeft.setPrefHeight(NAVIGATION_BUTTON_SIZE);
        upperLeft.setOnAction(event -> gameController.nextTurn(Move.UPPER_LEFT));
        upperLeft.setText("⬉");
        upperLeft.setStyle("-fx-font: 20 calibri;");

        up.setPrefWidth(NAVIGATION_BUTTON_SIZE);
        up.setPrefHeight(NAVIGATION_BUTTON_SIZE);
        up.setOnAction(event -> gameController.nextTurn(Move.UP));
        up.setText("⬆");
        up.setStyle("-fx-font: 20 calibri;");

        upperRight.setPrefWidth(NAVIGATION_BUTTON_SIZE);
        upperRight.setPrefHeight(NAVIGATION_BUTTON_SIZE);
        upperRight.setOnAction(event -> gameController.nextTurn(Move.UPPER_RIGHT));
        upperRight.setText("⬈");
        upperRight.setStyle("-fx-font: 20 calibri;");

        right.setPrefWidth(NAVIGATION_BUTTON_SIZE);
        right.setPrefHeight(NAVIGATION_BUTTON_SIZE);
        right.setOnAction(event -> gameController.nextTurn(Move.RIGHT));
        right.setText("➡");
        right.setStyle("-fx-font: 20 calibri;");

        lowerRight.setPrefWidth(NAVIGATION_BUTTON_SIZE);
        lowerRight.setPrefHeight(NAVIGATION_BUTTON_SIZE);
        lowerRight.setOnAction(event -> gameController.nextTurn(Move.LOWER_RIGHT));
        lowerRight.setText("⬊");
        lowerRight.setStyle("-fx-font: 20 calibri;");

        wait.setPrefWidth(NAVIGATION_BUTTON_SIZE);
        wait.setPrefHeight(NAVIGATION_BUTTON_SIZE);
        wait.setOnAction(event -> gameController.nextTurn(Move.WAIT));
        wait.setText("●");
        wait.setStyle("-fx-font: 20 calibri;");

        teleport.setPrefWidth(1.5 * NAVIGATION_BUTTON_SIZE);
        teleport.setPrefHeight(1.5 * NAVIGATION_BUTTON_SIZE);
        teleport.setOnAction(event -> gameController.nextTurn(Move.TELEPORT));
        teleport.setText("TELEPORT");

        restart.setPrefWidth(1.5 * NAVIGATION_BUTTON_SIZE);
        restart.setPrefHeight(NAVIGATION_BUTTON_SIZE);
        restart.setText("RESET");
        restart.setOnAction(gameController::handleRestart);

        controls.add(down, 1, 2);
        controls.add(left, 0, 1);
        controls.add(right, 2, 1);
        controls.add(up, 1, 0);
        controls.add(lowerLeft, 0, 2);
        controls.add(lowerRight, 2, 2);
        controls.add(upperLeft, 0, 0);
        controls.add(upperRight, 2, 0);
        controls.add(wait, 1, 1);

        BorderPane lowerBar = new BorderPane();

        VBox metrics = new VBox(10);
        VBox specialButtons = new VBox();

        specialButtons.getChildren().add(teleport);
        specialButtons.getChildren().add(restart);
        specialButtons.setPadding(new Insets(30, 30, 30, 30));
        specialButtons.setSpacing(30);

        setLabel(levelLabel, "Level:", metrics);
        setLabel(numberOfLivesLabel, "Number of lives:", metrics);
        setLabel(numberOfTeleportersLabel, "Number of teleporters:", metrics);
        setLabel(scoreLabel, "Score:", metrics);
        setLabel(highScoreLabel, "Highest score:", metrics);

        metrics.setPadding(new Insets(50, 30, 30, 30));

        lowerBar.setLeft(metrics);
        lowerBar.setCenter(controls);
        controls.setTranslateX((double) NATIVE_BOARD_WIDTH * 0.05);
        controls.setTranslateY(25);
        lowerBar.setRight(specialButtons);
        lowerBar.setTop(new Region());

        this.getChildren().add(lowerBar);
    }

    private void setLabel(Label numericLabel, String labelText, VBox metrics) {
        BorderPane label = new BorderPane();
        label.setLeft(new Label(labelText));
        label.setRight(numericLabel);
        numericLabel.setPadding(new Insets(0, 0, 0, 5));
        metrics.getChildren().add(label);
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
        numberOfLivesLabel.textProperty().bind(gameState.getNumberOfLivesProperty().asString());
        numberOfTeleportersLabel.textProperty().bind(gameState.getNumberOfTeleportersProperty().asString());
        scoreLabel.textProperty().bind(gameState.getCurrentScoreProperty().asString());
        highScoreLabel.textProperty().bind(gameState.getHighestScoreProperty().asString());
        levelLabel.textProperty().bind(gameState.getLevelProperty().asString());

        gameState.getNumberOfTeleportersProperty()
                .addListener((ObservableValue<? extends Number> observableValue, Number number, Number t1) -> {
                    if (t1.intValue() == 0) {
                        teleport.setDisable(true);
                    }
                    if (number.intValue() == 0) {
                        teleport.setDisable(false);
                    }
                });

        gameState.getNumberOfLivesProperty()
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
        teleport.setDisable(f);
    }

    /**
     * Unfreeze game.
     */
    public void unfreezeGame() {
        setGameControlsDisable(false);
    }
}
