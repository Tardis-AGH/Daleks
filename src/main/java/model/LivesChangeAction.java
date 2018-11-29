package model;

/**
 * Action implementation that changes the lives counter kept in {@link GameState};
 * Used when the Doctor picks up a heart power-up or dies.
 */

public class LivesChangeAction implements Action {
    private int livesChange;

    /**
     * Class constructor
     *
     * @param livesChange change in the number of lives
     */

    public LivesChangeAction(int livesChange) {
        this.livesChange = livesChange;
    }

    @Override
    public Status execute(Game game) {
        int currentNumberOfLives = game.getGameState().getNumberOfLives();
        game.getGameState().setNumberOfLives(currentNumberOfLives + livesChange);

        if (livesChange >= 0) return Status.CONTINUE_GAME;

        if (currentNumberOfLives > 0) return Status.RESTART_GAME;

        return Status.GAME_OVER;
    }


    public int getLivesChange() {
        return livesChange;
    }

    public void setLivesChange(int livesChange) {
        this.livesChange = livesChange;
    }
}
