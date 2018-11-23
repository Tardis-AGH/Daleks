package model;

import java.util.HashMap;
import java.util.List;

public class Board {

    private HashMap<Coordinates, BoardElement> elements;
    private Doctor doctor;

    public List<DynamicBoardElement> getDynamicBoardElements() {
        //TODO
        return null;
    }

    public List<StaticBoardElement> getStaticBoardElements() {
        //TODO
        return null;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public HashMap<Coordinates, BoardElement> getElements() {
        return elements;
    }
}
