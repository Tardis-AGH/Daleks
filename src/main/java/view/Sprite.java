package view;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.image.ImageView;
import model.board.Coordinates;
import model.element.BoardElement;

/**
 * The type Sprite.
 */
public class Sprite extends ImageView implements ChangeListener<Coordinates> {

    /**
     * Instantiates a new Sprite.
     *
     * @param element the element
     */
    public Sprite(BoardElement element) {
        element.getCoordinatesProperty().addListener(this);
    }

    @Override
    public void changed(ObservableValue<? extends Coordinates> observableValue, Coordinates coordinates,
            Coordinates t1) {

    }
}
