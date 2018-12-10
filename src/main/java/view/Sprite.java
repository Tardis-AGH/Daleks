package view;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import model.board.Board;
import model.board.Coordinates;
import model.element.BoardElement;

public class Sprite extends ImageView implements ChangeListener<Coordinates> {

    private GameWindow gameWindow;

    public Sprite(BoardElement element, Image image, GameWindow gameWindow) {
        super(image);
        double scale = image.getWidth()/image.getHeight();
        double baseSize  = (double)(GameWindow.getNativeBoardWidth()/ Board.getBoardWidth());

        this.setFitHeight(baseSize*scale);
        this.setFitWidth(baseSize);

        element.getCoordinatesProperty().addListener(this);
        this.gameWindow=gameWindow;
        this.gameWindow.getTiles().add(this, element.getCoordinates().getX(), element.getCoordinates().getY());
    }

    @Override
    public void changed(ObservableValue<? extends Coordinates> observableValue, Coordinates coordinates, Coordinates t1) {
        this.gameWindow.getTiles().getChildren().removeAll(this);
        this.gameWindow.getTiles().add(this, coordinates.getX(), coordinates.getY());
        System.out.println("Changing things");
    }
}
