package model.action.gamestate;

import model.action.Action;
import model.game.Game;
import model.game.Status;

/**
 * Action implementation that changes the lives counter kept in {@link model.game.GameState}.
 * Used when the Doctor picks up a heart power-up or dies.
 */
public class LivesChangeAction implements Action {

    private final int livesChange;

    /**
     * Class constructor.
     *
     * @param livesChange change in the number of lives
     */
    public LivesChangeAction(int livesChange) {
        this.livesChange = livesChange;
    }

    @Override
    public Status execute(Game game) {
        final int currentNumberOfLives = game.getGameState().getNumberOfLives();
        game.getGameState().setNumberOfLives(currentNumberOfLives + livesChange);

        if (livesChange >= 0) {
            return Status.CONTINUE_GAME;
        }

        if (currentNumberOfLives > 0) {
            return Status.RESTART_LEVEL;
        }

        return Status.GAME_OVER;
    }
}
