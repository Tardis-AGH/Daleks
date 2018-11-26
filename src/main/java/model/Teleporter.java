package model;

import java.util.List;

public class Teleporter extends StaticBoardElement {

    public Teleporter(Coordinates coordinates) {
        super(coordinates);
    }

    @Override
    public List<Action> accept(DynamicBoardElement visitor) {
        return visitor.visit(this);
    }
}
