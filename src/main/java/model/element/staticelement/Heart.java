package model.element.staticelement;

import javafx.scene.image.Image;
import model.Coordinates;
import model.InteractionResult;
import model.element.DynamicBoardElement;
import model.element.StaticBoardElement;

/**
 * The type Heart.
 */
public class Heart extends StaticBoardElement {

    /**
     * Instantiates a new Heart.
     *
     * @param coordinates the coordinates
     */
    public Heart(Coordinates coordinates) {
        super(coordinates);
    }

    /**
     * Instantiates a new Heart.
     *
     * @param coordinates the coordinates
     * @param sprite the sprite
     */
    public Heart(Coordinates coordinates, Image sprite) {
        super(coordinates, sprite);
    }

    @Override
    public InteractionResult accept(DynamicBoardElement visitor) {
        return visitor.visit(this);
    }
}
