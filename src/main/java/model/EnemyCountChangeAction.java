package model;

public class EnemyCountChangeAction implements Action {

    private int enemyCountChange;

    public EnemyCountChangeAction(int enemyCountChange) {
        this.enemyCountChange = enemyCountChange;
    }

    @Override
    public Status execute(Game game) {
        int newEnemyCount = game.getGameState().getEnemyCount() + enemyCountChange;

        if (newEnemyCount == 0)
            return Status.LEVEL_UP;

        game.getGameState().setEnemyCount(newEnemyCount);
        return Status.CONTINUE_GAME;
    }
}
