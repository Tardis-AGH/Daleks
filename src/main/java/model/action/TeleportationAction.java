package model.action;

import model.game.Game;
import model.game.Status;

/**
 * The type Teleportation action.
 */
public class TeleportationAction implements Action {

    @Override
    public Status execute(Game game) {
        if (game.getGameState().getNumberOfTeleporters() == 0) {
            return Status.SKIP_MOVE;
        } else {
            int currentNumber = game.getGameState().getNumberOfTeleporters();
            game.getGameState().setNumberOfTeleporters(currentNumber - 1);
            game.getBoard().getDoctor().setCoordinates(game.getBoard().getRandomCoordinates());
            return Status.CONTINUE_GAME;
        }
    }
}

