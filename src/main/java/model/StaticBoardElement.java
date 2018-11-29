package model;

import javafx.scene.image.Image;

abstract class StaticBoardElement extends BoardElement {

    public StaticBoardElement(Coordinates coordinates) {
        super(coordinates);
    }

    public StaticBoardElement(Coordinates coordinates, Image sprite) {
        super(coordinates, sprite);
    }

}
