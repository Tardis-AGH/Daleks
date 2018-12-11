package view;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import model.board.Coordinates;
import model.element.BoardElement;

public class Sprite extends ImageView implements ChangeListener<Coordinates> {

    private GridPane tiles;

    public Sprite(BoardElement element, Image image, GridPane tiles, int boardWidth) {
        super(image);
        double scale = image.getWidth() / image.getHeight();
        double baseSize = (double) (GameWindow.getNativeBoardWidth() / boardWidth);

        this.setFitHeight(baseSize*scale);
        this.setFitWidth(baseSize);

        element.getCoordinatesProperty().addListener(this);
        this.tiles = tiles;
        tiles.add(this, element.getCoordinates().getX(), element.getCoordinates().getY());
    }

    @Override
    public void changed(ObservableValue<? extends Coordinates> observableValue, Coordinates coordinates, Coordinates t1) {

        tiles.getChildren().remove(this);

        if (t1 != null)
            tiles.add(this, t1.getX(), t1.getY());
    }
}