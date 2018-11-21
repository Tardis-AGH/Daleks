package model;

import javafx.scene.image.Image;

abstract class StaticBoardElement extends BoardElement {

    StaticBoardElement(Coordinates coordinates, Image sprite) {
        super(coordinates, sprite);
    }

}
