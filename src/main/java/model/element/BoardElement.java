package model.element;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.image.Image;
import model.board.Coordinates;
import model.game.InteractionResult;

/**
 * The type Board element.
 */
public abstract class BoardElement {

    private final ObjectProperty<Coordinates> coordinates = new SimpleObjectProperty<>();
    private Image sprite;

    /**
     * Instantiates a new Board element.
     *
     * @param coordinates the coordinates
     */
    public BoardElement(Coordinates coordinates) {
        this.coordinates.setValue(coordinates);
    }

    /**
     * Accept list.
     *
     * @param visitor the visitor
     *
     * @return the list
     */
    public abstract InteractionResult accept(DynamicBoardElement visitor);

    /**
     * Gets coordinates.
     *
     * @return the coordinates
     */
    public Coordinates getCoordinates() {
        return coordinates.get();
    }

    /**
     * Sets coordinates.
     *
     * @param coordinates the coordinates
     */
    public void setCoordinates(Coordinates coordinates) {
        this.coordinates.setValue(coordinates);
    }

    /**
     * Gets coordinates property.
     *
     * @return the coordinates property
     */
    public ObjectProperty<Coordinates> getCoordinatesProperty() {
        return coordinates;
    }

    /**
     * Gets image path.
     *
     * @return path to image
     */
    public abstract String getImagePath();

}
