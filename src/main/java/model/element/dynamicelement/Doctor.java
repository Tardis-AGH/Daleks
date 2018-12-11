package model.element.dynamicelement;

import model.action.Action;
import model.action.ElementDeletionAction;
import model.action.LivesChangeAction;
import model.action.TeleportersChangeAction;
import model.board.Coordinates;
import model.board.Move;
import model.board.generator.CoordinatesGenerator;
import model.element.DynamicBoardElement;
import model.element.staticelement.Heart;
import model.element.staticelement.ScrapPile;
import model.element.staticelement.Teleporter;
import model.game.InteractionResult;

import java.util.LinkedList;
import java.util.List;

/**
 * The type Doctor.
 */
public class Doctor extends DynamicBoardElement {

    private static final String SPRITE_PATH = "images/doctor/doctor.png";
    private final CoordinatesGenerator coordinatesGenerator;

    /**
     * Instantiates a new Doctor.
     *
     * @param coordinates the coordinates
     * @param coordinatesGenerator the coordinates generator
     */
    public Doctor(Coordinates coordinates, CoordinatesGenerator coordinatesGenerator) {
        super(coordinates);
        this.coordinatesGenerator = coordinatesGenerator;
    }

    /**
     * Instantiates a new Doctor.
     *
     * @param coordinates the coordinates
     * @param coordinatesGenerator the coordinates generator
     * @param newSpritePath the new sprite path
     */
    public Doctor(Coordinates coordinates, CoordinatesGenerator coordinatesGenerator, String newSpritePath) {
        super(coordinates);
        this.coordinatesGenerator = coordinatesGenerator;
    }

    /**
     * Make move list.
     *
     * @param move user input*
     *
     * @return list of actions generated by doctor movement
     */
    public List<Action> makeMove(Move move) {
        final LinkedList<Action> actions = new LinkedList<>();

        if (move == Move.TELEPORT) {
            setCoordinates(coordinatesGenerator.getRandomCoordinates());
            actions.add(new TeleportersChangeAction(-1));
        } else {
            setCoordinates(getCoordinates().getUpdated(move));
        }
        return actions;
    }

    @Override
    public InteractionResult accept(DynamicBoardElement visitor) {
        return visitor.visit(this);
    }

    @Override
    public InteractionResult visit(Dalek dalek) {
        // the Doctor is processed before the Daleks
        return null;
    }

    @Override
    public InteractionResult visit(Heart heart) {
        final InteractionResult interactionResult = new InteractionResult(this);
        interactionResult.addAction(new LivesChangeAction(+1));
        interactionResult.addAction(new ElementDeletionAction(heart));
        return interactionResult;
    }

    @Override
    public InteractionResult visit(Doctor doctor) {
        // only one Doctor per game
        return null;
    }

    @Override
    public InteractionResult visit(Teleporter teleporter) {
        final InteractionResult interactionResult = new InteractionResult(this);
        interactionResult.addAction(new TeleportersChangeAction(1));
        interactionResult.addAction(new ElementDeletionAction(teleporter));
        return interactionResult;
    }

    @Override
    public InteractionResult visit(ScrapPile scrapPile) {
        final InteractionResult interactionResult = new InteractionResult(scrapPile);
        interactionResult.addAction(new LivesChangeAction(-1));
        return interactionResult;
    }

    public String getImagePath(){
        return SPRITE_PATH;
    }
}
