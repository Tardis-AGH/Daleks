package model.element.staticelement;

import java.util.List;
import javafx.scene.image.Image;
import model.Coordinates;
import model.action.Action;
import model.element.DynamicBoardElement;
import model.element.StaticBoardElement;

/**
 * The type Scrap pile.
 */
public class ScrapPile extends StaticBoardElement {

    /**
     * Instantiates a new Scrap pile.
     *
     * @param coordinates the coordinates
     */
    public ScrapPile(Coordinates coordinates) {
        super(coordinates);
    }

    /**
     * Instantiates a new Scrap pile.
     *
     * @param coordinates the coordinates
     * @param sprite the sprite
     */
    public ScrapPile(Coordinates coordinates, Image sprite) {
        super(coordinates, sprite);
    }

    @Override
    public List<Action> accept(DynamicBoardElement visitor) {
        return visitor.visit(this);
    }
}
