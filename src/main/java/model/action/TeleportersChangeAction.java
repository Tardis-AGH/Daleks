package model.action;

import model.game.Game;
import model.game.Status;

/**
 * Action implementation that changes the teleporters counter kept in {@link model.game.GameState}.
 * Used when the Doctor picks up a teleporter power-up or teleports.
 */
public class TeleportersChangeAction implements Action {

    private final int teleportersChange;

    /**
     * Class constructor.
     *
     * @param teleportersChange change in the number of teleporters
     */
    public TeleportersChangeAction(int teleportersChange) {
        this.teleportersChange = teleportersChange;
    }

    @Override
    public Status execute(Game game) {
        int newTeleporters = game.getGameState().getNumberOfTeleporters() + teleportersChange;

        if (newTeleporters < 0) {
            newTeleporters = 0;
        }
        game.getGameState().setNumberOfTeleporters(newTeleporters);

        return Status.CONTINUE_GAME;
    }
}
