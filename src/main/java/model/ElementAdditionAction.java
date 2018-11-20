package model;

public class ElementAdditionAction implements Action {
    private BoardElement element;

    public ElementAdditionAction(BoardElement element) {
        this.element = element;
    }

    @Override
    public Status execute(Game game) {
        //TODO
        return null;
    }


}
