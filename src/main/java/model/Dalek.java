package model;

import javafx.scene.image.Image;

import java.util.LinkedList;
import java.util.List;

public class Dalek extends DynamicBoardElement {

    public Dalek(Coordinates coordinates) {
        super(coordinates);
    }

    public Dalek(Coordinates coordinates, Image sprite) {
        super(coordinates, sprite);
    }

    public void makeMove(Coordinates doctorCoordinates) {
        int dalekX = getCoordinates().getX();
        int dalekY = getCoordinates().getY();

        int doctorX = doctorCoordinates.getX();
        int doctorY = doctorCoordinates.getY();

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
        List<Action> actions = new LinkedList<>();
        ScrapPile scrapPile = new ScrapPile(this.getCoordinates());
        actions.add(new ElementAdditionAction(scrapPile));
        actions.add(new ScoreChangeAction(2));
        actions.add(new EnemyCountChangeAction(-2));
        return actions;
    }

    @Override
    public List<Action> visit(Heart heart) {
        List<Action> actions = new LinkedList<>();
        actions.add(new ElementAdditionAction(this));
        return actions;
    }

    @Override
    public List<Action> visit(Doctor doctor) {
        List<Action> actions = new LinkedList<>();
        actions.add(new ElementAdditionAction(this));
        actions.add(new LivesChangeAction(-1));
        return actions;
    }

    @Override
    public List<Action> visit(Teleporter teleporter) {
        List<Action> actions = new LinkedList<>();
        actions.add(new ElementAdditionAction(this));
        return actions;
    }

    @Override
    public List<Action> visit(ScrapPile scrapPile) {
        List<Action> actions = new LinkedList<>();
        actions.add(new ElementAdditionAction(scrapPile));
        actions.add(new ScoreChangeAction(1));
        actions.add(new EnemyCountChangeAction(-1));
        return actions;
    }
}