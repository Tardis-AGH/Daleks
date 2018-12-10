package model.element.staticelement;

import model.InteractionResult;
import model.board.Coordinates;
import model.element.DynamicBoardElement;
import model.element.StaticBoardElement;

/**
 * The type Teleporter.
 */
public class Teleporter extends StaticBoardElement {

    private static final String SPRITE_PATH = "images/dalek/dalek5.png";

    /**
     * Instantiates a new Teleporter.
     *
     * @param coordinates the coordinates
     */
    public Teleporter(Coordinates coordinates) {
        super(coordinates);
    }

    @Override
    public InteractionResult accept(DynamicBoardElement visitor) {
        return visitor.visit(this);
    }
}
