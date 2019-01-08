package view;

import controller.GameController;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import model.board.Move;

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

    private final GridPane tiles;

    private Button downButton;
    private Button lowerLeftButton;
    private Button leftButton;
    private Button upperLeftButton;
    private Button upButton;
    private Button upperRightButton;
    private Button rightButton;
    private Button lowerRightButton;
    private Button waitButton;
    private Button teleporterButton;
    private Button bombButton;

    private Label numberOfLivesLabel;
    private Label numberOfTeleportersLabel;
    private Label numberOfBombsLabel;
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
        int preferredTileSize = NATIVE_BOARD_WIDTH / gameController.getGame().getBoardWidth();
        Stream<ColumnConstraints> columns = Stream.generate(ColumnConstraints::new)
                .peek(e -> e.setPrefWidth(preferredTileSize))
                .limit(gameController.getGame().getBoardWidth());

        Stream<RowConstraints> rows = Stream.generate(RowConstraints::new)
                .peek(e -> e.setPrefHeight(preferredTileSize))
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
        createKeyBindings(gameController);

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
        numberOfBombsLabel = new Label();
        numberOfTeleportersLabel = new Label();
        scoreLabel = new Label();
        highScoreLabel = new Label();

        metrics.getChildren().add(createLabelHolder(levelLabel, "Level:"));
        metrics.getChildren().add(createLabelHolder(numberOfLivesLabel, "Number of lives:"));
        metrics.getChildren().add(createLabelHolder(numberOfBombsLabel, "Number of bombs:"));
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

        upButton = createMovementButton("⬆", gameController, Move.UP);
        upperRightButton = createMovementButton("⬈", gameController, Move.UPPER_RIGHT);
        rightButton = createMovementButton("➡", gameController, Move.RIGHT);
        lowerRightButton = createMovementButton("⬊", gameController, Move.LOWER_RIGHT);
        downButton = createMovementButton("⬇", gameController, Move.DOWN);
        lowerLeftButton = createMovementButton("⬋", gameController, Move.LOWER_LEFT);
        leftButton = createMovementButton("⬅", gameController, Move.LEFT);
        upperLeftButton = createMovementButton("⬉", gameController, Move.UPPER_LEFT);
        waitButton = createMovementButton("●", gameController, Move.WAIT);

        controls.add(upButton, 1, 0);
        controls.add(upperRightButton, 2, 0);
        controls.add(rightButton, 2, 1);
        controls.add(lowerRightButton, 2, 2);
        controls.add(downButton, 1, 2);
        controls.add(lowerLeftButton, 0, 2);
        controls.add(leftButton, 0, 1);
        controls.add(upperLeftButton, 0, 0);
        controls.add(waitButton, 1, 1);

        return controls;
    }

    private Button createMovementButton(String buttonText, GameController gameController, Move move) {
        Button button = new Button();
        button.setPrefWidth(NAVIGATION_BUTTON_SIZE);
        button.setPrefHeight(NAVIGATION_BUTTON_SIZE);
        button.setOnAction(event -> gameController.nextTurn(move));
        button.setText(buttonText);
        button.setStyle("-fx-font: 25 calibri;");

        return button;
    }

    private VBox createSpecialButtons(GameController gameController) {
        VBox specialButtons = new VBox();

        teleporterButton = new Button();
        teleporterButton.setPrefWidth(1.5 * NAVIGATION_BUTTON_SIZE);
        teleporterButton.setPrefHeight(1.5 * NAVIGATION_BUTTON_SIZE);
        teleporterButton.setOnAction(event -> gameController.nextTurn(Move.TELEPORT));
        teleporterButton.setText("TELEPORT");

        bombButton = new Button();
        bombButton.setPrefWidth(1.5 * NAVIGATION_BUTTON_SIZE);
        bombButton.setPrefHeight(NAVIGATION_BUTTON_SIZE);
        bombButton.setOnAction(event -> gameController.nextTurn(Move.BOMB));
        bombButton.setText("BOMB");

        Button restart = new Button();
        restart.setPrefWidth(1.5 * NAVIGATION_BUTTON_SIZE);
        restart.setPrefHeight(NAVIGATION_BUTTON_SIZE);
        restart.setOnAction(gameController::handleRestart);
        restart.setText("RESET");

        specialButtons.getChildren().add(teleporterButton);
        specialButtons.getChildren().add(bombButton);
        specialButtons.getChildren().add(restart);
        specialButtons.setPadding(new Insets(30, 30, 30, 30));
        specialButtons.setSpacing(10);

        return specialButtons;
    }

    private void createKeyBindings(GameController gameController) {
        this.setOnKeyPressed(e -> {
            switch (e.getCode()) {
                case W:
                    gameController.nextTurn(Move.UP);
                    break;
                case E:
                    gameController.nextTurn(Move.UPPER_RIGHT);
                    break;
                case D:
                    gameController.nextTurn(Move.RIGHT);
                    break;
                case C:
                    gameController.nextTurn(Move.LOWER_RIGHT);
                    break;
                case X:
                    gameController.nextTurn(Move.DOWN);
                    break;
                case Z:
                    gameController.nextTurn(Move.LOWER_LEFT);
                    break;
                case A:
                    gameController.nextTurn(Move.LEFT);
                    break;
                case Q:
                    gameController.nextTurn(Move.UPPER_LEFT);
                    break;
                case S:
                    gameController.nextTurn(Move.WAIT);
                    break;
                case T:
                    gameController.nextTurn(Move.TELEPORT);
                    break;
                case R:
                    gameController.handleRestart(e);
                    break;
                case B:
                    gameController.nextTurn(Move.BOMB);
            }
        });
    }

    /**
     * Create sprite image image view.
     *
     * @param imagePath the image path
     * @param boardWidth the board width
     *
     * @return the image view
     */
    public ImageView createSpriteImage(String imagePath, int boardWidth) {
        Image image = new javafx.scene.image.Image(
                Objects.requireNonNull(getClass().getClassLoader().getResource(imagePath)).toExternalForm());

        final double scale = image.getWidth() / image.getHeight();
        final double baseSize = 0.92 * (double) (NATIVE_BOARD_WIDTH / boardWidth);

        ImageView spriteImage = new ImageView(image);
        spriteImage.setFitHeight(baseSize * scale);
        spriteImage.setFitWidth(baseSize);

        return spriteImage;
    }

    /**
     * Gets tiles.
     *
     * @return the tiles
     */
    public GridPane getTiles() {
        return tiles;
    }

    /**
     * Gets down button.
     *
     * @return the down button
     */
    public Button getDownButton() {
        return downButton;
    }

    /**
     * Gets lower left button.
     *
     * @return the lower left button
     */
    public Button getLowerLeftButton() {
        return lowerLeftButton;
    }

    /**
     * Gets left button.
     *
     * @return the left button
     */
    public Button getLeftButton() {
        return leftButton;
    }

    /**
     * Gets upper left button.
     *
     * @return the upper left button
     */
    public Button getUpperLeftButton() {
        return upperLeftButton;
    }

    /**
     * Gets up button.
     *
     * @return the up button
     */
    public Button getUpButton() {
        return upButton;
    }

    /**
     * Gets upper right button.
     *
     * @return the upper right button
     */
    public Button getUpperRightButton() {
        return upperRightButton;
    }

    /**
     * Gets right button.
     *
     * @return the right button
     */
    public Button getRightButton() {
        return rightButton;
    }

    /**
     * Gets lower right button.
     *
     * @return the lower right button
     */
    public Button getLowerRightButton() {
        return lowerRightButton;
    }

    /**
     * Gets wait button.
     *
     * @return the wait button
     */
    public Button getWaitButton() {
        return waitButton;
    }

    /**
     * Gets teleporter button.
     *
     * @return the teleporter button
     */
    public Button getTeleporterButton() {
        return teleporterButton;
    }

    /**
     * Gets bomb button.
     *
     * @return the bomb button
     */
    public Button getBombButton() {
        return bombButton;
    }

    /**
     * Gets number of lives label.
     *
     * @return the number of lives label
     */
    public Label getNumberOfLivesLabel() {
        return numberOfLivesLabel;
    }

    /**
     * Gets number of teleporters label.
     *
     * @return the number of teleporters label
     */
    public Label getNumberOfTeleportersLabel() {
        return numberOfTeleportersLabel;
    }

    /**
     * Gets number of bombs label.
     *
     * @return the number of bombs label
     */
    public Label getNumberOfBombsLabel() {
        return numberOfBombsLabel;
    }

    /**
     * Gets score label.
     *
     * @return the score label
     */
    public Label getScoreLabel() {
        return scoreLabel;
    }

    /**
     * Gets high score label.
     *
     * @return the high score label
     */
    public Label getHighScoreLabel() {
        return highScoreLabel;
    }

    /**
     * Gets level label.
     *
     * @return the level label
     */
    public Label getLevelLabel() {
        return levelLabel;
    }
}
