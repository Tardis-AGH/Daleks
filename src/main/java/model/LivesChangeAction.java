package model;

public class LivesChangeAction implements Action {
    private boolean lifeGained;

    public LivesChangeAction(int liveChange) {
        this.lifeGained = liveChange >= 0;
    }

    @Override
    public Status execute(Game game) {
        int currentNumberOfLives = game.getGameState().getNumberOfLives();

        if (lifeGained) {
            game.getGameState().setNumberOfLives(currentNumberOfLives + 1);
            return Status.CONTINUE_GAME;
        }

        if (currentNumberOfLives > 0) {
            game.getGameState().setNumberOfLives(currentNumberOfLives - 1);
            return Status.RESTART_GAME;
        }

        return Status.GAME_OVER;
    }


}
