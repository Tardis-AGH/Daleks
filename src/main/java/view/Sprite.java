package view;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.image.ImageView;
import model.Coordinates;
import model.element.BoardElement;

public class Sprite extends ImageView implements ChangeListener<Coordinates> {

    public Sprite(BoardElement element) {
        super(element.getSprite());
        element.getCoordinatesProperty().addListener(this);
    }

    @Override
    public void changed(ObservableValue<? extends Coordinates> observableValue, Coordinates coordinates, Coordinates t1) {

    }
}
