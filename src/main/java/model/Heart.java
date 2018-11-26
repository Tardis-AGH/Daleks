package model;

import java.util.List;

public class Heart extends StaticBoardElement {
    public Heart(Coordinates coordinates) {
        super(coordinates);
    }

    @Override
    public List<Action> accept(DynamicBoardElement visitor) {
        return visitor.visit(this);
    }
}
