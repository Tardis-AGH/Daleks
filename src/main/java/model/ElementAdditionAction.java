package model;

/**
 * Action implementation that adds a board elements into the map in {@link Board};
 * Used when an element moves into an already occupied grid cell and result of the interaction
 * is an another element ending up in the cell.
 */

public class ElementAdditionAction implements Action {
    private BoardElement element;

    /**
     * Class constructor
     * E.g. when the Doctor steps into a cell, in which a heart power-up is placed the {@link Doctor#visit(Heart)} method
     * this action would be instantiated in the following way:
     * new ElementAdditionAction(this)
     *
     * @param element the element to be put into the map
     */
    public ElementAdditionAction(BoardElement element) {
        this.element = element;
    }

    @Override
    public Status execute(Game game) {
        game.getBoard().getElements().put(element.getCoordinates(), element);

        return Status.CONTINUE_GAME;
    }


}
