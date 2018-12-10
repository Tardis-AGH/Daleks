package model.element.staticelement;

import model.board.Coordinates;
import model.element.DynamicBoardElement;
import model.element.StaticBoardElement;
import model.game.InteractionResult;

/**
 * The type Heart.
 */
public class Heart extends StaticBoardElement {

    private static final String SPRITE_PATH = "images/dalek/dalek5.png";

    /**
     * Instantiates a new Heart.
     *
     * @param coordinates the coordinates
     */
    public Heart(Coordinates coordinates) {
        super(coordinates);
    }

    @Override
    public InteractionResult accept(DynamicBoardElement visitor) {
        return visitor.visit(this);
    }
}
