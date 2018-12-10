package model.element;

import model.board.Coordinates;

/**
 * The type Static board element.
 */
public abstract class StaticBoardElement extends BoardElement {

    /**
     * Instantiates a new Static board element.
     *
     * @param coordinates the coordinates
     */
    public StaticBoardElement(Coordinates coordinates) {
        super(coordinates);
    }
}
