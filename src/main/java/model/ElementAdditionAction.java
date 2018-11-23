package model;

public class ElementAdditionAction implements Action {
    private BoardElement element;

    public ElementAdditionAction(BoardElement element) {
        this.element = element;
    }

    @Override
    public Status execute(Game game) {
        game.getBoard().getElements().put(element.getCoordinates(), element);

        return Status.CONTINUE_GAME;
    }


}
