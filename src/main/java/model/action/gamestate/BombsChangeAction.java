package model.action.gamestate;

import model.action.Action;
import model.game.Game;
import model.game.Status;

/**
 * Action implementation that changes the bombs counter kept in {@link model.game.GameState}.
 */
public class BombsChangeAction implements Action {

    private final int bombsChange;

    /**
     * Class constructor.
     *
     * @param bombsChange change in the number of bombs
     */
    public BombsChangeAction(int bombsChange) {
        this.bombsChange = bombsChange;
    }

    @Override
    public Status execute(Game game) {
        int newBombs = game.getGameState().getNumberOfBombs() + bombsChange;

        if (newBombs < 0) {
            newBombs = 0;
        }
        game.getGameState().setNumberOfBombs(newBombs);

        return Status.CONTINUE_GAME;
    }
}
