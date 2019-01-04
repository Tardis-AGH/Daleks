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
 * Action implementation that EXTERMINATES any Dalek within one cell radius around the Doctor.
 */
public class BombAction implements Action {

    @Override
    public Status execute(Game game) {
        if (game.getGameState().getNumberOfBombs() == 0) return Status.SKIP_MOVE;

        final int currentNumber = game.getGameState().getNumberOfBombs();
        game.getGameState().setNumberOfBombs(currentNumber - 1);

        return game.getBoard().getDaleks().stream()
                .filter(d -> d.getCoordinates().distance(game.getBoard().getDoctor().getCoordinates()) == 1)
                .map(d -> {
                    Coordinates c = d.getCoordinates().getUpdated(Move.BOMB);
                    new ElementAdditionAction(new ScrapPile(c)).execute(game);
                    return new ElementDeletionAction(d).execute(game);
                })
                .max(Comparator.comparing(Status::ordinal))
                .orElse(Status.CONTINUE_GAME);
    }
}

