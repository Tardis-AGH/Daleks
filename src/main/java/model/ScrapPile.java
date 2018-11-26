package model;

import javafx.scene.image.Image;

import java.util.List;

public class ScrapPile extends StaticBoardElement {

    public ScrapPile(Coordinates coordinates) {
        super(coordinates);
    }

    public ScrapPile(Coordinates coordinates, Image sprite) {
        super(coordinates, sprite);
    }

    @Override
    public List<Action> accept(DynamicBoardElement visitor) {
        return visitor.visit(this);
    }
}
