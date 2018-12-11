package view;

import controller.GameController;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableSet;
import javafx.collections.SetChangeListener;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.image.Image;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import model.board.Move;
import model.element.BoardElement;
import model.game.GameState;

import java.util.stream.Collectors;
import java.util.stream.Stream;


public class GameWindow extends VBox {

    //presenter window resolution
    private static final int NATIVE_BOARD_WIDTH = 600;
    private static final int NATIVE_BOARD_HEIGHT = 800;

    private static final int NAVIGATION_BUTTON_SIZE = 60;

    private Button down = new Button();
    private Button lowerLeft = new Button();
    private Button left = new Button();
    private Button upperLeft = new Button();
    private Button up = new Button();
    private Button upperRight = new Button();
    private Button right = new Button();
    private Button lowerRight = new Button();
    private Button wait = new Button();
    private Button restart = new Button();
    private Button teleport = new Button();

    Label numberOfLivesLabel = new Label();
    Label numberOfTeleportersLabel = new Label();
    Label scoreLabel = new Label();
    Label highScoreLabel = new Label();
    Label levelLabel = new Label();

    private GridPane tiles;

    public GameWindow(GameController gameController) {
        this.setPrefSize(NATIVE_BOARD_WIDTH, NATIVE_BOARD_HEIGHT);

        GridPane gridPane = new GridPane();
        gridPane.setGridLinesVisible(true);
        int prefferedTileSize = NATIVE_BOARD_WIDTH / gameController.getGame().getBoardWidth();
        Stream<ColumnConstraints> columns = Stream
                .generate(ColumnConstraints::new)
                .peek(e -> {
                    e.setPrefWidth(prefferedTileSize);
                })
                .limit(gameController.getGame().getBoardWidth());

        Stream<RowConstraints> rows = Stream
                .generate(RowConstraints::new)
                .peek(e -> {
                    e.setPrefHeight(prefferedTileSize);
                })
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

        lowerLeft.setPrefWidth(NAVIGATION_BUTTON_SIZE);
        lowerLeft.setPrefHeight(NAVIGATION_BUTTON_SIZE);
        lowerLeft.setOnAction(event -> gameController.nextTurn(Move.LOWER_LEFT));
        lowerLeft.setText("⬋");

        left.setPrefWidth(NAVIGATION_BUTTON_SIZE);
        left.setPrefHeight(NAVIGATION_BUTTON_SIZE);
        left.setOnAction(event -> gameController.nextTurn(Move.LEFT));
        left.setText("⬅");

        upperLeft.setPrefWidth(NAVIGATION_BUTTON_SIZE);
        upperLeft.setPrefHeight(NAVIGATION_BUTTON_SIZE);
        upperLeft.setOnAction(event -> gameController.nextTurn(Move.UPPER_LEFT));
        upperLeft.setText("⬉");

        up.setPrefWidth(NAVIGATION_BUTTON_SIZE);
        up.setPrefHeight(NAVIGATION_BUTTON_SIZE);
        up.setOnAction(event -> gameController.nextTurn(Move.UP));
        up.setText("⬆");

        upperRight.setPrefWidth(NAVIGATION_BUTTON_SIZE);
        upperRight.setPrefHeight(NAVIGATION_BUTTON_SIZE);
        upperRight.setOnAction(event -> gameController.nextTurn(Move.UPPER_RIGHT));
        upperRight.setText("⬈");

        right.setPrefWidth(NAVIGATION_BUTTON_SIZE);
        right.setPrefHeight(NAVIGATION_BUTTON_SIZE);
        right.setOnAction(event -> gameController.nextTurn(Move.RIGHT));
        right.setText("➡");

        lowerRight.setPrefWidth(NAVIGATION_BUTTON_SIZE);
        lowerRight.setPrefHeight(NAVIGATION_BUTTON_SIZE);
        lowerRight.setOnAction(event -> gameController.nextTurn(Move.LOWER_RIGHT));
        lowerRight.setText("⬊");

        wait.setPrefWidth(NAVIGATION_BUTTON_SIZE);
        wait.setPrefHeight(NAVIGATION_BUTTON_SIZE);
        wait.setOnAction(event -> gameController.nextTurn(Move.WAIT));
        wait.setText("W");

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

        BorderPane label;

        label = new BorderPane();
        label.setLeft(new Label("Number of lives:"));
        label.setRight(numberOfLivesLabel);
        metrics.getChildren().add(label);

        label = new BorderPane();
        label.setLeft(new Label("Number of teleporters:"));
        label.setRight(numberOfTeleportersLabel);
        numberOfTeleportersLabel.setPadding(new Insets(0, 0, 0, 5));
        metrics.getChildren().add(label);

        label = new BorderPane();
        label.setLeft(new Label("Score:"));
        label.setRight(scoreLabel);
        scoreLabel.setPadding(new Insets(0, 0, 0, 5));
        metrics.getChildren().add(label);

        label = new BorderPane();
        label.setLeft(new Label("Highest score:"));
        label.setRight(highScoreLabel);
        highScoreLabel.setPadding(new Insets(0, 0, 0, 5));
        metrics.getChildren().add(label);

        label = new BorderPane();
        label.setLeft(new Label("Level:"));
        label.setRight(levelLabel);
        levelLabel.setPadding(new Insets(0, 0, 0, 5));
        metrics.getChildren().add(label);

        metrics.setPadding(new Insets(50, 30, 30, 30));

        lowerBar.setLeft(metrics);
        lowerBar.setCenter(controls);
        controls.setTranslateX((double) NATIVE_BOARD_WIDTH * 0.05);
        controls.setTranslateY(25);
        lowerBar.setRight(specialButtons);
        lowerBar.setTop(new Region());

        this.getChildren().add(lowerBar);
    }

    public void initSprites(ObservableSet<BoardElement> elements, int boardWidth) {
        removeSprites();

        for (BoardElement boardElement : elements) createSprite(boardElement, boardWidth);

        elements.addListener((SetChangeListener.Change<? extends BoardElement> change) -> {
            if (change.wasAdded())
                createSprite(change.getElementAdded(), boardWidth);
        });
    }

    private void createSprite(BoardElement boardElement, int boardWidth) {
        Image image = new javafx.scene.image.Image(getClass().getClassLoader().getResource(boardElement.getImagePath()).toExternalForm());
        new Sprite(boardElement, image, tiles, boardWidth);
    }

    public void initGameStateProperties(GameState gameState) {
        numberOfLivesLabel.textProperty().bind(gameState.getNumberOfLivesProperty().asString());
        numberOfTeleportersLabel.textProperty().bind(gameState.getNumberOfTeleportersProperty().asString());
        scoreLabel.textProperty().bind(gameState.getCurrentScoreProperty().asString());
        highScoreLabel.textProperty().bind(gameState.getHighestScoreProperty().asString());
        levelLabel.textProperty().bind(gameState.getLevelProperty().asString());

        gameState.getNumberOfTeleportersProperty().addListener((ObservableValue<? extends Number> observableValue, Number number, Number t1) -> {
            if (t1.intValue() == 0) teleport.setDisable(true);
            if (number.intValue() == 0) teleport.setDisable(false);
        });

        gameState.getNumberOfLivesProperty().addListener((ObservableValue<? extends Number> observableValue, Number number, Number t1) -> {
            if (t1.intValue() < 0) {
                numberOfLivesLabel.textProperty().unbind();
                numberOfLivesLabel.setText("☠");
            }
        });
    }


    public static int getNativeBoardWidth() {
        return NATIVE_BOARD_WIDTH;
    }

    public static int getNativeBoardHeight() {
        return NATIVE_BOARD_HEIGHT;
    }

    public void freezeGame() {
        removeSprites();

        setGameControlsDisable(true);
    }

    public void unfreezeGame() {
        setGameControlsDisable(false);
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

    private void removeSprites() {
        tiles.getChildren().removeAll(
                tiles.getChildren().stream().filter(c -> c instanceof Sprite).collect(Collectors.toList())
        );
    }
}
