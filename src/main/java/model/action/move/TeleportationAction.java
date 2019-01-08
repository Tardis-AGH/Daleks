package model.action.move;

import model.action.Action;
import model.game.Game;
import model.game.Status;

/**
 * Action implementation that changes the Doctor's position when teleportation is selected as the move only if it is
 * possible (that is if the player has enough teleportations available).
 */
public class TeleportationAction implements Action {

    private static final int TELEPORTATION_RADIUS = 2;

    @Override
    public Status execute(Game game) {
        if (game.getGameState().getNumberOfTeleporters() == 0) {
            return Status.SKIP_MOVE;
        } else {
            final int currentNumber = game.getGameState().getNumberOfTeleporters();
            game.getGameState().setNumberOfTeleporters(currentNumber - 1);
            game.getBoard()
                    .getDoctor()
                    .setCoordinates(
                            game.getBoard().getCoordinateGenerator().getRandomCoordinates(TELEPORTATION_RADIUS));
            return Status.CONTINUE_GAME;
        }
    }
}

