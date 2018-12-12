package model.action.element;

import model.action.Action;
import model.element.BoardElement;
import model.element.dynamicelement.Dalek;
import model.game.Game;
import model.game.Status;

/**
 * Action implementation that removes a board elements from the set in {@link model.board.Board}.
 */
public class ElementDeletionAction implements Action {

    private final BoardElement element;

    /**
     * Class constructor.
     *
     * @param element the element to be removed from the set
     */
    public ElementDeletionAction(BoardElement element) {
        this.element = element;
    }

    @Override
    public Status execute(Game game) {
        element.setCoordinates(null);
        game.getBoard().getElements().remove(element);
        if (element instanceof Dalek && game.getBoard().getDaleks().size() == 0) {
            return Status.LEVEL_UP;
        }
        return Status.CONTINUE_GAME;
    }
}
