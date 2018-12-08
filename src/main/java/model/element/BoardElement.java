package model.element;

import java.util.List;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import model.Coordinates;
import model.action.Action;

/**
 * The type Board element.
 */
public abstract class BoardElement {

    private Coordinates coordinates;
    private Image sprite;

    /**
     * Instantiates a new Board element.
     *
     * @param coordinates the coordinates
     */
    public BoardElement(Coordinates coordinates) {
        this.coordinates = coordinates;
        // TODO: this.sprite
    }

    /**
     * Instantiates a new Board element.
     *
     * @param coordinates the coordinates
     * @param sprite the sprite
     */
    public BoardElement(Coordinates coordinates, Image sprite) {
        this.coordinates = coordinates;
        this.sprite = sprite;
    }

    /**
     * Accept list.
     *
     * @param visitor the visitor
     *
     * @return the list
     */
    public abstract List<Action> accept(DynamicBoardElement visitor);

    /**
     * Render.
     *
     * @param graphicsContext the graphics context
     */
    public void render(GraphicsContext graphicsContext) {
        //TODO
    }

    /**
     * Gets sprite.
     *
     * @return the sprite
     */
    public Image getSprite() {
        return sprite;
    }

    /**
     * Sets sprite.
     *
     * @param sprite the sprite
     */
    public void setSprite(Image sprite) {
        this.sprite = sprite;
    }

    /**
     * Gets coordinates.
     *
     * @return the coordinates
     */
    public Coordinates getCoordinates() {
        return coordinates;
    }

    /**
     * Sets coordinates.
     *
     * @param coordinates the coordinates
     */
    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }
}
