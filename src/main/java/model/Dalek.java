package model;

import javafx.scene.image.Image;

import java.util.List;

public class Dalek extends DynamicBoardElement {

    public Dalek(Coordinates coordinates, Image sprite) {
        super(coordinates, sprite);
    }

    public void makeMove(Coordinates doctorCoordinates) {
        //TODO
    }

    @Override
    public List<Action> accept(DynamicBoardElement visitor) {
        return visitor.visit(this);
    }

    @Override
    public List<Action> visit(Dalek dalek) {
        //TODO
        return null;
    }

    @Override
    public List<Action> visit(Heart heart) {
        //TODO
        return null;
    }

    @Override
    public List<Action> visit(Doctor doctor) {
        //TODO
        return null;
    }

    @Override
    public List<Action> visit(Teleporter teleporter) {
        //TODO
        return null;
    }

    @Override
    public List<Action> visit(ScrapPile scrapPile) {
        //TODO
        return null;
    }
}
