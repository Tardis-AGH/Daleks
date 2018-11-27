package model;

import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class Doctor extends DynamicBoardElement {

    public Doctor(Coordinates coordinates) {
        super(coordinates);
    }

    public Doctor(Coordinates coordinates, Image sprite) {
        super(coordinates, sprite);
    }

    public void makeMove(Move move) {

        Coordinates oldCoordinates = this.getCoordinates();
        Coordinates newCoordinates= new Coordinates(oldCoordinates);

        switch (move){
            case UP:
                newCoordinates.setY(oldCoordinates.getY()+1);
                break;
            case UPPER_RIGHT:
                newCoordinates.setY(oldCoordinates.getY()+1);
                newCoordinates.setX(oldCoordinates.getX()+1);
                break;
            case RIGHT:
                newCoordinates.setX(oldCoordinates.getX()+1);
                break;
            case LOWER_RIGHT:
                newCoordinates.setY(oldCoordinates.getY()-1);
                newCoordinates.setX(oldCoordinates.getX()+1);
                break;
            case DOWN:
                newCoordinates.setY(oldCoordinates.getY()-1);
                break;
            case LOWER_LEFT:
                newCoordinates.setY(oldCoordinates.getY()-1);
                newCoordinates.setX(oldCoordinates.getX()-1);
                break;
            case LEFT:
                newCoordinates.setX(oldCoordinates.getX()-1);
                break;
            case UPPER_LEFT:
                newCoordinates.setY(oldCoordinates.getY()+1);
                newCoordinates.setX(oldCoordinates.getX()-1);
                break;
        }

        if(this.isFieldInBounds(newCoordinates))
            this.setCoordinates(newCoordinates);
    }

    @Override
    public List<Action> accept(DynamicBoardElement visitor) {
        return visitor.visit(this);
    }

    @Override
    public List<Action> visit(Dalek dalek) {
        List<Action>actions = new ArrayList<>();
        actions.add(new LivesChangeAction(-1));
        return actions;
    }

    @Override
    public List<Action> visit(Heart heart) {
        List<Action>actions = new ArrayList<>();
        actions.add(new LivesChangeAction(+1));
        actions.add(new ElementAdditionAction(this));
        return actions;
    }

    @Override
    public List<Action> visit(Doctor doctor) {
        //NOT GONNA HAPPEN AS WE IMPLEMENT 1 DOCTOR PER GAME
        return null;
    }

    @Override
    public List<Action> visit(Teleporter teleporter) {
        List<Action>actions = new ArrayList<>();
        actions.add(new TeleportersChangeAction(-1));
        Coordinates newCoordinates = getNewCoordinates();
        this.setCoordinates(newCoordinates);
        actions.add(new ElementAdditionAction(this));
        return actions;
    }

    @Override
    public List<Action> visit(ScrapPile scrapPile) {
        List<Action>actions = new ArrayList<>();
        actions.add(new LivesChangeAction(-1));
        return actions;
    }


    private Coordinates getNewCoordinates(){

        return null;
    }

    private boolean isFieldEmpty(Coordinates coordinates){

        return true;
    }

    private boolean isFieldInBounds(Coordinates coordinates){

        return true;
    }

}
