package model.element.dynamicelement;

import java.util.LinkedList;
import java.util.List;
import javafx.scene.image.Image;
import model.Coordinates;
import model.action.Action;
import model.action.ElementAdditionAction;
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
        final int dalekX = getCoordinates().getX();
        final int dalekY = getCoordinates().getY();

        final int doctorX = doctorCoordinates.getX();
        final int doctorY = doctorCoordinates.getY();

        int newDalekX = dalekX;
        int newDalekY = dalekY;

        if (dalekX < doctorX) {
            newDalekX++;
        } else if (dalekX > doctorX) {
            newDalekX--;
        }
        if (dalekY < doctorY) {
            newDalekY++;
        } else if (dalekY > doctorY) {
            newDalekY--;
        }
        getCoordinates().setX(newDalekX);
        getCoordinates().setY(newDalekY);
    }

    @Override
    public List<Action> accept(DynamicBoardElement visitor) {
        return visitor.visit(this);
    }

    @Override
    public List<Action> visit(Dalek dalek) {
        final List<Action> actions = new LinkedList<>();
        final ScrapPile scrapPile = new ScrapPile(this.getCoordinates());
        actions.add(new ElementAdditionAction(scrapPile));
        actions.add(new ScoreChangeAction(2));
        actions.add(new EnemyCountChangeAction(-2));
        return actions;
    }

    @Override
    public List<Action> visit(Heart heart) {
        final List<Action> actions = new LinkedList<>();
        actions.add(new ElementAdditionAction(this));
        return actions;
    }

    @Override
    public List<Action> visit(Doctor doctor) {
        final List<Action> actions = new LinkedList<>();
        actions.add(new ElementAdditionAction(this));
        actions.add(new LivesChangeAction(-1));
        return actions;
    }

    @Override
    public List<Action> visit(Teleporter teleporter) {
        final List<Action> actions = new LinkedList<>();
        actions.add(new ElementAdditionAction(this));
        return actions;
    }

    @Override
    public List<Action> visit(ScrapPile scrapPile) {
        final List<Action> actions = new LinkedList<>();
        actions.add(new ElementAdditionAction(scrapPile));
        actions.add(new ScoreChangeAction(1));
        actions.add(new EnemyCountChangeAction(-1));
        return actions;
    }
}
