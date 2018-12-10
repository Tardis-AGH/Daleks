package model;

import java.util.LinkedList;
import java.util.List;
import model.action.Action;
import model.element.BoardElement;

/**
 * Result of an interaction between elements.
 */
public class InteractionResult {

    private final List<Action> actionsToExecute;
    private final BoardElement fieldWinner;

    /**
     * Instantiates a new Interaction result.
     *
     * @param fieldWinner the field winner
     */
    public InteractionResult(BoardElement fieldWinner) {
        this.fieldWinner = fieldWinner;
        this.actionsToExecute = new LinkedList<>();
    }

    /**
     * Gets the board field winner to be added to a map in {@link model.Game#makeMoves(model.board.Coordinates.Move)}.
     *
     * @return the field winner
     */
    public BoardElement getFieldWinner() {
        return fieldWinner;
    }

    /**
     * Adds an action to the list of actions to execute on {@link model.Game}.
     *
     * @param action the action
     */
    public void addAction(Action action) {
        actionsToExecute.add(action);
    }

    /**
     * Gets the list of actions to execute on {@link model.Game}.
     *
     * @return the field winner
     */
    public List<Action> getActionsToExecute() {
        return actionsToExecute;
    }
}
