package model.element.dynamicelement;

import javafx.scene.image.Image;
import model.Coordinates;
import model.InteractionResult;
import model.action.ElementAdditionAction;
import model.action.ElementDeletionAction;
import model.action.EnemyCountChangeAction;
import model.action.LivesChangeAction;
import model.action.ScoreChangeAction;
import model.element.DynamicBoardElement;
import model.element.staticelement.Heart;
import model.element.staticelement.ScrapPile;
import model.element.staticelement.Teleporter;

/**
 * The type Dalek.
 */
public class Dalek extends DynamicBoardElement {

    /**
     * Instantiates a new Dalek.
     *
     * @param coordinates the coordinates
     */
    public Dalek(Coordinates coordinates) {
        super(coordinates);
    }

    /**
     * Instantiates a new Dalek.
     *
     * @param coordinates the coordinates
     * @param sprite the sprite
     */
    public Dalek(Coordinates coordinates, Image sprite) {
        super(coordinates, sprite);
    }

    /**
     * Make move.
     *
     * @param doctorCoordinates the doctor coordinates
     */
    public void makeMove(Coordinates doctorCoordinates) {
        int dalekX = getCoordinates().getX();
        int dalekY = getCoordinates().getY();

        final int doctorX = doctorCoordinates.getX();
        final int doctorY = doctorCoordinates.getY();

        if (dalekX < doctorX) {
            dalekX++;
        } else if (dalekX > doctorX) {
            dalekX--;
        }
        if (dalekY < doctorY) {
            dalekY++;
        } else if (dalekY > doctorY) {
            dalekY--;
        }
        setCoordinates(new Coordinates(dalekX, dalekY));
    }

    @Override
    public InteractionResult accept(DynamicBoardElement visitor) {
        return visitor.visit(this);
    }

    @Override
    public InteractionResult visit(Dalek dalek) {
        final ScrapPile scrapPile = new ScrapPile(this.getCoordinates());
        final InteractionResult interactionResult = new InteractionResult(scrapPile);
        interactionResult.addAction(new ElementAdditionAction(scrapPile));
        interactionResult.addAction(new ElementDeletionAction(this));
        interactionResult.addAction(new ElementDeletionAction(dalek));
        interactionResult.addAction(new ScoreChangeAction(2));
        interactionResult.addAction(new EnemyCountChangeAction(-2));
        return interactionResult;
    }

    @Override
    public InteractionResult visit(Heart heart) {
        final InteractionResult interactionResult = new InteractionResult(this);
        interactionResult.addAction(new ElementDeletionAction(heart));
        return interactionResult;
    }

    @Override
    public InteractionResult visit(Doctor doctor) {
        final InteractionResult interactionResult = new InteractionResult(this);
        interactionResult.addAction(new LivesChangeAction(-1));
        return interactionResult;
    }

    @Override
    public InteractionResult visit(Teleporter teleporter) {
        final InteractionResult interactionResult = new InteractionResult(this);
        interactionResult.addAction(new ElementDeletionAction(teleporter));
        return interactionResult;
    }

    @Override
    public InteractionResult visit(ScrapPile scrapPile) {
        final InteractionResult interactionResult = new InteractionResult(scrapPile);
        interactionResult.addAction(new ElementDeletionAction(this));
        interactionResult.addAction(new ScoreChangeAction(1));
        interactionResult.addAction(new EnemyCountChangeAction(-1));
        return interactionResult;
    }
}