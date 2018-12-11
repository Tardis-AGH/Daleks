package model.element.staticelement;

import model.board.Coordinates;
import model.element.DynamicBoardElement;
import model.element.StaticBoardElement;
import model.game.InteractionResult;

/**
 * The type Teleporter.
 */
public class Teleporter extends StaticBoardElement {

    private static final String SPRITE_PATH = "images/dalek/dalek.png";

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

    public String getImagePath(){
        return SPRITE_PATH;
    }
}
