package model;

public interface Action {

    /**
     * Executes one of the specified objects-game API actions.
     *
     * @param game the context of execution passed by itself
     * @return status of the game after the execution of this action
     * @see Game
     * @see Status
     */
    public Status execute(Game game);
}
