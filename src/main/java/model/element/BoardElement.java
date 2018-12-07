package model.element;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.image.Image;
import model.Coordinates;
import model.InteractionResult;

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
        // TODO: this.sprite
    }

    /**
     * Instantiates a new Board element.
     *
     * @param coordinates the coordinates
     * @param sprite the sprite
     */
    public BoardElement(Coordinates coordinates, Image sprite) {
        this.coordinates.setValue(coordinates);
        this.sprite = sprite;
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
     * Gets sprite.
     *
     * @return the sprite
     */
    public Image getSprite() {
        return sprite;
    }

    /**
     * Sets sprite.
     *
     * @param sprite the sprite
     */
    public void setSprite(Image sprite) {
        this.sprite = sprite;
    }

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
}
