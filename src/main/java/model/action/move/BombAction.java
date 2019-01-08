package model.action.move;

import java.util.Comparator;
import model.action.Action;
import model.action.element.ElementAdditionAction;
import model.action.element.ElementDeletionAction;
import model.board.Move;
import model.element.staticelement.ScrapPile;
import model.game.Game;
import model.game.Status;

/**
 * Action implementation that EXTERMINATES any Dalek within one cell radius around the Doctor.
 */
public class BombAction implements Action {

    @Override
    public Status execute(Game game) {
        if (game.getGameState().getNumberOfBombs() == 0) {
            return Status.SKIP_MOVE;
        }

        final int currentNumber = game.getGameState().getNumberOfBombs();
        game.getGameState().setNumberOfBombs(currentNumber - 1);

        return game.getBoard()
                .getDaleks()
                .stream()
                .filter(dalek -> dalek.getCoordinates().distance(game.getBoard().getDoctor().getCoordinates()) == 1)
                .map(dalek -> {
                    new ElementAdditionAction(new ScrapPile(dalek.getCoordinates().getUpdated(Move.BOMB))).execute(
                            game);
                    return new ElementDeletionAction(dalek).execute(game);
                })
                .max(Comparator.comparing(Status::ordinal))
                .orElse(Status.CONTINUE_GAME);
    }
}

