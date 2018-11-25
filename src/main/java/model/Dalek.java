package model;

import javafx.scene.image.Image;

import java.util.LinkedList;
import java.util.List;

public class Dalek extends DynamicBoardElement {

    public Dalek(Coordinates coordinates, Image sprite) {
        super(coordinates, sprite);
    }

    public void makeMove(Coordinates doctorCoordinates) {
        int dalekX = getCoordinates().getX();
        int dalekY = getCoordinates().getY();

        int doctorX = doctorCoordinates.getX();
        int doctorY = doctorCoordinates.getY();

        if (dalekX == doctorX) {
            if (dalekY < doctorY) {
                getCoordinates().setY(dalekY + 1);
            } else if (dalekY > doctorY) {
                getCoordinates().setY(dalekY - 1);
            }
        } else if (dalekY == doctorY) {
            if (dalekX < doctorX) {
                getCoordinates().setX(dalekX + 1);
            } else {
                getCoordinates().setX(dalekX - 1);
            }
        } else if (dalekX < doctorX)
            if (dalekY < doctorY) {
                getCoordinates().setY(dalekY + 1);
                getCoordinates().setX(dalekX + 1);
            } else {
                getCoordinates().setY(dalekY - 1);
                getCoordinates().setX(dalekX + 1);
            }
        else {
            if (dalekY < doctorY) {
                getCoordinates().setY(dalekY + 1);
                getCoordinates().setX(dalekX - 1);
            } else {
                getCoordinates().setY(dalekY - 1);
                getCoordinates().setX(dalekX - 1);
            }
        }
    }

    @Override
    public List<Action> accept(DynamicBoardElement visitor) {
        return visitor.visit(this);
    }

    @Override
    public List<Action> visit(Dalek dalek) {
        List<Action> actions = new LinkedList<>();
        //TODO: sprite
        ScrapPile scrapPile = new ScrapPile(getCoordinates(), null);
        actions.add(new ElementAdditionAction(scrapPile));
        actions.add(new ScoreChangeAction(2));
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
        //TODO
        return null;
    }

    @Override
    public List<Action> visit(ScrapPile scrapPile) {
        List<Action> actions = new LinkedList<>();
        actions.add(new ElementAdditionAction(scrapPile));
        actions.add(new ScoreChangeAction(1));
        return actions;
    }
}
