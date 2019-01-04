package model.action.move;

import model.action.Action;
import model.action.element.ElementAdditionAction;
import model.action.element.ElementDeletionAction;
import model.board.Move;
import model.board.coordinates.Coordinates;
import model.element.staticelement.ScrapPile;
import model.game.Game;
import model.game.Status;

import java.util.Comparator;

/**
 * Action implementation that changes the Doctor's position when teleportation is selected as the move only if it is
 * possible (that is if the player has enough teleportations available).
 */
public class BombAction implements Action {

    @Override
    public Status execute(Game game) {
//        if (game.getGameState().getNumberOfTeleporters() == 0) {
//            return Status.SKIP_MOVE;
//        } else {
//            final int currentNumber = game.getGameState().getNumberOfTeleporters();
//            game.getGameState().setNumberOfTeleporters(currentNumber - 1);
//            game.getBoard().getDoctor().setCoordinates(game.getBoard().getCoordinateGenerator().getRandomCoordinates());
        return game.getBoard().getDaleks().stream()
                .filter(d -> d.getCoordinates().distance(game.getBoard().getDoctor().getCoordinates()) == 1)
                .map(d -> {
                    Coordinates c = d.getCoordinates().getUpdated(Move.BOMB);
                    new ElementAdditionAction(new ScrapPile(c)).execute(game);
                    return new ElementDeletionAction(d).execute(game);
                })
                .max(Comparator.comparing(Status::ordinal))
                .orElse(Status.CONTINUE_GAME);

//            return Status.CONTINUE_GAME;
//        }
    }
}

