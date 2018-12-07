package model.element.staticelement;

import javafx.scene.image.Image;
import model.Coordinates;
import model.InteractionResult;
import model.element.DynamicBoardElement;
import model.element.StaticBoardElement;

/**
 * The type Teleporter.
 */
public class Teleporter extends StaticBoardElement {

    /**
     * Instantiates a new Teleporter.
     *
     * @param coordinates the coordinates
     */
    public Teleporter(Coordinates coordinates) {
        super(coordinates);
    }

    /**
     * Instantiates a new Teleporter.
     *
     * @param coordinates the coordinates
     * @param sprite the sprite
     */
    public Teleporter(Coordinates coordinates, Image sprite) {
        super(coordinates, sprite);
    }

    @Override
    public InteractionResult accept(DynamicBoardElement visitor) {
        return visitor.visit(this);
    }
}
