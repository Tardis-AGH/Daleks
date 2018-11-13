package model;

import java.util.List;

public class ScrapPile extends StaticBoardElement {

    @Override
    public List<Action> accept(DynamicBoardElement visitor) {
        return visitor.visit(this);
    }
}
