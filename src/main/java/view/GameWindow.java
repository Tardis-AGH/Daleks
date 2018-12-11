package view;

import controller.GameController;
import javafx.collections.ObservableSet;
import javafx.collections.SetChangeListener;
import javafx.scene.control.Button;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import model.board.Move;
import model.element.BoardElement;

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
        down.setTranslateX(((double) NATIVE_BOARD_WIDTH / 2) - ((double) NAVIGATION_BUTTON_SIZE / 2));
        down.setTranslateY(2 * NAVIGATION_BUTTON_SIZE);
        down.setPrefWidth(NAVIGATION_BUTTON_SIZE);
        down.setPrefHeight(NAVIGATION_BUTTON_SIZE);
        down.setOnAction(event -> gameController.nextTurn(Move.DOWN));
        down.setText("v");

        lowerLeft.setTranslateX((int) ((NATIVE_BOARD_WIDTH / 2) - ((double) (3 * NAVIGATION_BUTTON_SIZE / 2))));
        lowerLeft.setTranslateY(2 * NAVIGATION_BUTTON_SIZE);
        lowerLeft.setPrefWidth(NAVIGATION_BUTTON_SIZE);
        lowerLeft.setPrefHeight(NAVIGATION_BUTTON_SIZE);
        lowerLeft.setOnAction(event -> gameController.nextTurn(Move.LOWER_LEFT));
        lowerLeft.setText("LL");

        left.setTranslateX((int) ((NATIVE_BOARD_WIDTH / 2) - ((double) (3 * NAVIGATION_BUTTON_SIZE / 2))));
        left.setTranslateY((NAVIGATION_BUTTON_SIZE));
        left.setPrefWidth(NAVIGATION_BUTTON_SIZE);
        left.setPrefHeight(NAVIGATION_BUTTON_SIZE);
        left.setOnAction(event -> gameController.nextTurn(Move.LEFT));
        left.setText("<");

        upperLeft.setTranslateX((int) ((NATIVE_BOARD_WIDTH / 2) - ((double) (3 * NAVIGATION_BUTTON_SIZE / 2))));
        upperLeft.setPrefWidth(NAVIGATION_BUTTON_SIZE);
        upperLeft.setPrefHeight(NAVIGATION_BUTTON_SIZE);
        upperLeft.setOnAction(event -> gameController.nextTurn(Move.UPPER_LEFT));
        upperLeft.setText("UL");

        up.setTranslateX(((double) NATIVE_BOARD_WIDTH / 2) - ((double) NAVIGATION_BUTTON_SIZE / 2));
        up.setPrefWidth(NAVIGATION_BUTTON_SIZE);
        up.setPrefHeight(NAVIGATION_BUTTON_SIZE);
        up.setOnAction(event -> gameController.nextTurn(Move.UP));
        up.setText("^");

        upperRight.setTranslateX(((double) NATIVE_BOARD_WIDTH / 2) + ((double) NAVIGATION_BUTTON_SIZE / 2));
        upperRight.setPrefWidth(NAVIGATION_BUTTON_SIZE);
        upperRight.setPrefHeight(NAVIGATION_BUTTON_SIZE);
        upperRight.setOnAction(event -> gameController.nextTurn(Move.UPPER_RIGHT));
        upperRight.setText("UR");

        right.setTranslateX(((double) NATIVE_BOARD_WIDTH / 2) + ((double) NAVIGATION_BUTTON_SIZE / 2));
        right.setTranslateY((NAVIGATION_BUTTON_SIZE));
        right.setPrefWidth(NAVIGATION_BUTTON_SIZE);
        right.setPrefHeight(NAVIGATION_BUTTON_SIZE);
        right.setOnAction(event -> gameController.nextTurn(Move.RIGHT));
        right.setText(">");

        lowerRight.setTranslateX(((double) NATIVE_BOARD_WIDTH / 2) + ((double) NAVIGATION_BUTTON_SIZE / 2));
        lowerRight.setTranslateY(2 * NAVIGATION_BUTTON_SIZE);
        lowerRight.setPrefWidth(NAVIGATION_BUTTON_SIZE);
        lowerRight.setPrefHeight(NAVIGATION_BUTTON_SIZE);
        lowerRight.setOnAction(event -> gameController.nextTurn(Move.LOWER_RIGHT));
        lowerRight.setText("LR");

        wait.setTranslateX(((double) NATIVE_BOARD_WIDTH / 2) - ((double) NAVIGATION_BUTTON_SIZE / 2));
        wait.setTranslateY((NAVIGATION_BUTTON_SIZE));
        wait.setPrefWidth(NAVIGATION_BUTTON_SIZE);
        wait.setPrefHeight(NAVIGATION_BUTTON_SIZE);
        wait.setOnAction(event -> gameController.nextTurn(Move.WAIT));
        wait.setText("W");

        teleport.setTranslateX(((double) NATIVE_BOARD_WIDTH / 6));
        teleport.setTranslateY((NAVIGATION_BUTTON_SIZE));
        teleport.setPrefWidth(1.5 * NAVIGATION_BUTTON_SIZE);
        teleport.setPrefHeight(1.5 * NAVIGATION_BUTTON_SIZE);
        teleport.setOnAction(event -> gameController.nextTurn(Move.TELEPORT));
        teleport.setText("TELEPORT");

        restart.setTranslateX(((double) 5 * NATIVE_BOARD_WIDTH / 6));
        restart.setTranslateY((NAVIGATION_BUTTON_SIZE));
        restart.setPrefWidth(1.5 * NAVIGATION_BUTTON_SIZE);
        restart.setPrefHeight(NAVIGATION_BUTTON_SIZE);
        restart.setText("REST");
        restart.setOnAction(gameController::handleRestart);

        controls.getChildren().add(down);
        controls.getChildren().add(left);
        controls.getChildren().add(right);
        controls.getChildren().add(up);
        controls.getChildren().add(lowerLeft);
        controls.getChildren().add(lowerRight);
        controls.getChildren().add(upperLeft);
        controls.getChildren().add(upperRight);
        controls.getChildren().add(wait);
        controls.getChildren().add(teleport);
        controls.getChildren().add(restart);

        this.getChildren().add(controls);
    }

    public void initSprites(ObservableSet<BoardElement> elements, int boardWidth) {
        removeSprites();

        for (BoardElement boardElement : elements)
            new Sprite(boardElement, tiles, boardWidth);

        elements.addListener((SetChangeListener.Change<? extends BoardElement> change) -> {
            if (change.wasAdded())
                new Sprite(change.getElementAdded(), tiles, boardWidth);
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

        down.setDisable(true);
        lowerLeft.setDisable(true);
        left.setDisable(true);
        upperLeft.setDisable(true);
        up.setDisable(true);
        upperRight.setDisable(true);
        right.setDisable(true);
        lowerRight.setDisable(true);
        wait.setDisable(true);
        teleport.setDisable(true);
    }

    public void unfreezeGame() {
        down.setDisable(false);
        lowerLeft.setDisable(false);
        left.setDisable(false);
        upperLeft.setDisable(false);
        up.setDisable(false);
        upperRight.setDisable(false);
        right.setDisable(false);
        lowerRight.setDisable(false);
        wait.setDisable(false);
        teleport.setDisable(false);
    }

    private void removeSprites() {
        tiles.getChildren().removeAll(
                tiles.getChildren().stream().filter(c -> c instanceof Sprite).collect(Collectors.toList())
        );
    }
}
