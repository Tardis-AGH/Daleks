package model.action;

import model.Game;
import model.Status;

/**
 * Action implementation that changes the enemy counter kept in {@link model.GameState}.
 * Used whenever the number of enemies on the board decreases
 * after any of Daleks meets its end.
 */
public class EnemyCountChangeAction implements Action {

    private final int enemyCountChange;

    /**
     * Class constructor
     * E.g. when two Daleks crush into each other, leaving a pile of scrap metal behind
     * this action would be instantiated in the visitor method in the following fashion:
     * new EnemyCountChangeAction(-2)
     *
     * @param enemyCountChange change in the number of enemies on the board
     */
    public EnemyCountChangeAction(int enemyCountChange) {
        this.enemyCountChange = enemyCountChange;
    }

    @Override
    public Status execute(Game game) {
        final int newEnemyCount = game.getGameState().getEnemyCount() + enemyCountChange;
        game.getGameState().setEnemyCount(newEnemyCount);

        if (newEnemyCount <= 0) {
            return Status.LEVEL_UP;
        }

        return Status.CONTINUE_GAME;
    }
}
