package model.element.staticelement;

import model.board.coordinates.Coordinates;
import model.element.DynamicBoardElement;
import model.element.StaticBoardElement;
import model.game.InteractionResult;

/**
 * The type Heart.
 */
public class Bomb extends StaticBoardElement {

    private static final String SPRITE_PATH = "images/powerup/bomb.png";

    /**
     * Instantiates a new Bomb.
     *
     * @param coordinates the coordinates
     */
    public Bomb(Coordinates coordinates) {
        super(coordinates);
    }

    @Override
    public InteractionResult accept(DynamicBoardElement visitor) {
        return visitor.visit(this);
    }

    public String getImagePath() {
        return SPRITE_PATH;
    }

}
