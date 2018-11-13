package model;

import java.util.List;

public abstract class BoardElement {
    private int x;
    private int y;

    public abstract List<Action> accept(DynamicBoardElement visitor);
}
