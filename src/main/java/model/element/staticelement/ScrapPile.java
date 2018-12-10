package model.element.staticelement;

import model.InteractionResult;
import model.board.Coordinates;
import model.element.DynamicBoardElement;
import model.element.StaticBoardElement;

/**
 * The type Scrap pile.
 */
public class ScrapPile extends StaticBoardElement {

    private static final String SPRITE_PATH = "images/dalek/dalek5.png";

    /**
     * Instantiates a new Scrap pile.
     *
     * @param coordinates the coordinates
     */
    public ScrapPile(Coordinates coordinates) {
        super(coordinates);
    }

    @Override
    public InteractionResult accept(DynamicBoardElement visitor) {
        return visitor.visit(this);
    }
}
