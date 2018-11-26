package model;

import java.util.List;

public class ScrapPile extends StaticBoardElement {

    public ScrapPile(Coordinates coordinates) {
        super(coordinates);
    }

    @Override
    public List<Action> accept(DynamicBoardElement visitor) {
        return visitor.visit(this);
    }
}
