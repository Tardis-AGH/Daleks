package model;

import javafx.scene.image.Image;

import java.util.List;

public class Teleporter extends StaticBoardElement {

    public Teleporter(Coordinates coordinates) {
        super(coordinates);
    }

    public Teleporter(Coordinates coordinates, Image sprite) {
        super(coordinates, sprite);
    }

    @Override
    public List<Action> accept(DynamicBoardElement visitor) {
        return visitor.visit(this);
    }
}
