package model.action;

import model.Game;
import model.Status;

/**
 * The interface Action.
 */
public interface Action {

    /**
     * Executes one of the specified objects-game API actions.
     *
     * @param game the context of execution passed by itself
     *
     * @return status of the game after the execution of this action
     *
     * @see model.Game
     * @see model.Status
     */
    Status execute(Game game);
}
