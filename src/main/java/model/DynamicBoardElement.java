package model;

import java.util.List;

public abstract class DynamicBoardElement extends BoardElement {

    DynamicBoardElement(Coordinates coordinates) {
        super(coordinates);
    }

    public abstract List<Action> visit(Heart heart);

    public abstract List<Action> visit(Teleporter teleporter);

    public abstract List<Action> visit(ScrapPile scrapPile);

    public abstract List<Action> visit(Doctor doctor);

    public abstract List<Action> visit(Dalek dalek);
}
