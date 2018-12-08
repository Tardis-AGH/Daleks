package model.element;

import java.util.List;
import javafx.scene.image.Image;
import model.Coordinates;
import model.action.Action;
import model.element.dynamicelement.Dalek;
import model.element.dynamicelement.Doctor;
import model.element.staticelement.Heart;
import model.element.staticelement.ScrapPile;
import model.element.staticelement.Teleporter;

/**
 * The type Dynamic board element.
 */
public abstract class DynamicBoardElement extends BoardElement {

    /**
     * Instantiates a new Dynamic board element.
     *
     * @param coordinates the coordinates
     */
    public DynamicBoardElement(Coordinates coordinates) {
        super(coordinates);
    }

    /**
     * Instantiates a new Dynamic board element.
     *
     * @param coordinates the coordinates
     * @param sprite the sprite
     */
    public DynamicBoardElement(Coordinates coordinates, Image sprite) {
        super(coordinates, sprite);
    }

    /**
     * Visit list.
     *
     * @param heart the heart
     *
     * @return the list
     */
    public abstract List<Action> visit(Heart heart);

    /**
     * Visit list.
     *
     * @param teleporter the teleporter
     *
     * @return the list
     */
    public abstract List<Action> visit(Teleporter teleporter);

    /**
     * Visit list.
     *
     * @param scrapPile the scrap pile
     *
     * @return the list
     */
    public abstract List<Action> visit(ScrapPile scrapPile);

    /**
     * Visit list.
     *
     * @param doctor the doctor
     *
     * @return the list
     */
    public abstract List<Action> visit(Doctor doctor);

    /**
     * Visit list.
     *
     * @param dalek the dalek
     *
     * @return the list
     */
    public abstract List<Action> visit(Dalek dalek);
}
