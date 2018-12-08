package model.element;

import javafx.scene.image.Image;
import model.Coordinates;

/**
 * The type Static board element.
 */
public abstract class StaticBoardElement extends BoardElement {

    /**
     * Instantiates a new Static board element.
     *
     * @param coordinates the coordinates
     */
    public StaticBoardElement(Coordinates coordinates) {
        super(coordinates);
    }

    /**
     * Instantiates a new Static board element.
     *
     * @param coordinates the coordinates
     * @param sprite the sprite
     */
    public StaticBoardElement(Coordinates coordinates, Image sprite) {
        super(coordinates, sprite);
    }

}
