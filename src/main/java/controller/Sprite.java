package controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import model.board.Coordinates;
import model.element.BoardElement;

/**
 * The type Sprite.
 */
public class Sprite implements ChangeListener<Coordinates> {

    private final GridPane tiles;
    private final ImageView spriteImage;

    /**
     * Instantiates a new Sprite.
     *
     * @param element the element
     * @param tiles the tiles
     */
    public Sprite(BoardElement element, ImageView spriteImage, GridPane tiles) {
        this.spriteImage = spriteImage;

        element.getCoordinatesProperty().addListener(this);
        this.tiles = tiles;
        tiles.add(spriteImage, element.getCoordinates().getX(), element.getCoordinates().getY());
    }

    @Override
    public void changed(ObservableValue<? extends Coordinates> observableValue, Coordinates coordinates,
            Coordinates t1) {

        tiles.getChildren().remove(spriteImage);

        if (t1 != null) {
            tiles.add(spriteImage, t1.getX(), t1.getY());
        }
    }
}
