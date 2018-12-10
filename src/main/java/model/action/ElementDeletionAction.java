package model.action;

import model.element.BoardElement;
import model.game.Game;
import model.game.Status;

/**
 * Action implementation that removes a board elements from the set in {@link model.board.Board}.
 */
public class ElementDeletionAction implements Action {

    private final BoardElement element;

    /**
     * Class constructor
     *
     * @param element the element to be removed from the set
     */
    public ElementDeletionAction(BoardElement element) {
        this.element = element;
    }

    @Override
    public Status execute(Game game) {
        game.getBoard().getElements().remove(element);

        return Status.CONTINUE_GAME;
    }
}
