package model;

import javafx.scene.image.Image;

import java.util.List;

public class Heart extends StaticBoardElement {
    public Heart(Coordinates coordinates, Image sprite) {
        super(coordinates, sprite);
    }

    @Override
    public List<Action> accept(DynamicBoardElement visitor) {
        return visitor.visit(this);
    }
}
