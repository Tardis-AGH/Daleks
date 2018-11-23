package model;

public class ScoreChangeAction implements Action {
    private int scoreChange;

    public ScoreChangeAction(int scoreChange) {
        this.scoreChange = scoreChange;
    }

    @Override
    public Status execute(Game game) {
        int newScore = game.getGameState().getCurrentScore() + scoreChange;
        game.getGameState().setCurrentScore(newScore);

        if (newScore > game.getGameState().getHighestScore())
            game.getGameState().setHighestScore(newScore);

        return Status.CONTINUE_GAME;
    }

}
