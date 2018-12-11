package model.element;

import model.board.Coordinates;
import model.element.dynamicelement.Dalek;
import model.element.dynamicelement.Doctor;
import model.element.staticelement.Heart;
import model.element.staticelement.ScrapPile;
import model.element.staticelement.Teleporter;
import model.game.InteractionResult;

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
     * Visit heart element upon collison.
     *
     * @param heart the heart
     *
     * @return the results of the interaction
     */
    public abstract InteractionResult visit(Heart heart);

    /**
     * Visit teleporter element upon collision.
     *
     * @param teleporter the teleporter
     *
     * @return the results of the interaction
     */
    public abstract InteractionResult visit(Teleporter teleporter);

    /**
     * Visit scrapPile element upon collision.
     *
     * @param scrapPile the scrap pile
     *
     * @return the results of the interaction
     */
    public abstract InteractionResult visit(ScrapPile scrapPile);

    /**
     * Visit the Doctor upon collision.
     *
     * @param doctor the doctor
     *
     * @return the results of the interaction
     */
    public abstract InteractionResult visit(Doctor doctor);

    /**
     * Visit a Dalek upon collision.
     *
     * @param dalek the dalek
     *
     * @return the results of the interaction
     */
    public abstract InteractionResult visit(Dalek dalek);
}
