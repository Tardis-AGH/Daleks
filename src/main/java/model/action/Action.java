package model.action;

import model.game.Game;
import model.game.Status;

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
     * @see model.game.Game
     * @see model.game.Status
     */
    Status execute(Game game);
}
