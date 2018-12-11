package model.element.staticelement;

import model.board.Coordinates;
import model.element.DynamicBoardElement;
import model.element.StaticBoardElement;
import model.game.InteractionResult;

/**
 * The type Scrap pile.
 */
public class ScrapPile extends StaticBoardElement {

    private static final String SPRITE_PATH = "images/dalek/dalek.png";

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

    public String getImagePath(){
        return SPRITE_PATH;
    }
}
