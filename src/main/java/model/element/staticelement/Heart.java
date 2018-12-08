package model.element.staticelement;

import java.util.List;
import javafx.scene.image.Image;
import model.Coordinates;
import model.action.Action;
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
    public List<Action> accept(DynamicBoardElement visitor) {
        return visitor.visit(this);
    }
}
