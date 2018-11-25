package model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.List;

public abstract class BoardElement {

    private Coordinates coordinates;
    private Image sprite;

    BoardElement(Coordinates coordinates, Image sprite) {
        this.coordinates = coordinates;
        this.sprite = sprite;
    }

    public abstract List<Action> accept(DynamicBoardElement visitor);


    public void render(GraphicsContext graphicsContext) {
        //TODO
    }

    public Image getSprite() {
        return sprite;
    }

    public void setSprite(Image sprite) {
        this.sprite = sprite;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }
}
