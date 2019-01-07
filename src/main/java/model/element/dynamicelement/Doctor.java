package model.element.dynamicelement;

import model.action.Action;
import model.action.element.ElementDeletionAction;
import model.action.gamestate.BombsChangeAction;
import model.action.gamestate.LivesChangeAction;
import model.action.gamestate.TeleportersChangeAction;
import model.action.move.BombAction;
import model.action.move.TeleportationAction;
import model.board.Move;
import model.board.coordinates.Coordinates;
import model.element.DynamicBoardElement;
import model.element.staticelement.Bomb;
import model.element.staticelement.Heart;
import model.element.staticelement.ScrapPile;
import model.element.staticelement.Teleporter;
import model.game.InteractionResult;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

/**
 * The type Doctor.
 */
public class Doctor extends DynamicBoardElement {

    private static final String[] SPRITES =
            {"doctor1.png", "doctor2.png", "doctor3.png", "doctor4.png", "doctor5.png", "doctor6.png", "doctor7.png",
                    "doctor8.png", "warDoctor.png", "doctor9.png", "doctor10.png", "doctor11.png", "doctor12.png",
                    "doctor13.png",};
    private static final String SPRITE_BASE_PATH = "images/doctor/";
    private String SPRITE_PATH;

    /**
     * Instantiates a new Doctor.
     *
     * @param coordinates the coordinates
     */
    public Doctor(Coordinates coordinates) {
        super(coordinates);
        setImage(0);
    }

    /**
     * Sets image.
     *
     * @param doctorDeaths the doctor deaths
     */
    public void setImage(int doctorDeaths) {
        SPRITE_PATH = new File(SPRITE_BASE_PATH, SPRITES[doctorDeaths % SPRITES.length]).getPath();
    }

    /**
     * Make move list.
     *
     * @param move user input
     *
     * @return list of actions generated by doctor movement
     */
    public List<Action> makeMove(Move move) {
        final LinkedList<Action> actions = new LinkedList<>();

        switch (move) {
            case TELEPORT:
                actions.add(new TeleportationAction());
                break;
            case BOMB:
                actions.add(new BombAction());
                break;

            default:
                setCoordinates(getCoordinates().getUpdated(move));
                break;
        }
        return actions;
    }

    @Override
    public InteractionResult accept(DynamicBoardElement visitor) {
        return visitor.visit(this);
    }

    @Override
    public InteractionResult visit(Dalek dalek) {
        throw new RuntimeException("The Doctor should be processed before the Daleks");
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
        throw new RuntimeException("Only one Doctor is allowed per game");
    }

    @Override
    public InteractionResult visit(Teleporter teleporter) {
        final InteractionResult interactionResult = new InteractionResult(this);
        interactionResult.addAction(new TeleportersChangeAction(1));
        interactionResult.addAction(new ElementDeletionAction(teleporter));
        return interactionResult;
    }

    @Override
    public InteractionResult visit(Bomb bomb) {
        final InteractionResult interactionResult = new InteractionResult(this);
        interactionResult.addAction(new BombsChangeAction(1));
        interactionResult.addAction(new ElementDeletionAction(bomb));
        return interactionResult;
    }

    @Override
    public InteractionResult visit(ScrapPile scrapPile) {
        final InteractionResult interactionResult = new InteractionResult(scrapPile);
        interactionResult.addAction(new LivesChangeAction(-1));
        return interactionResult;
    }

    public String getImagePath() {
        return SPRITE_PATH;
    }
}
