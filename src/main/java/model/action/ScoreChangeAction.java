package model.action;

import model.Game;
import model.Status;

/**
 * Action implementation that changes the score counter kept in {@link model.GameState}.
 */
public class ScoreChangeAction implements Action {

    private final int scoreChange;

    /**
     * Class constructor.
     *
     * @param scoreChange change in score
     */
    public ScoreChangeAction(int scoreChange) {
        this.scoreChange = scoreChange;
    }

    @Override
    public Status execute(Game game) {
        final int newScore = game.getGameState().getCurrentScore() + scoreChange;
        game.getGameState().setCurrentScore(newScore);

        if (newScore > game.getGameState().getHighestScore()) {
            game.getGameState().setHighestScore(newScore);
        }

        return Status.CONTINUE_GAME;
    }

}
