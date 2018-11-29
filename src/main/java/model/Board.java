package model;

import java.util.HashMap;
import java.util.List;

public class Board {

    private static int BOARD_WIDTH;
    private static int BOARD_HEIGHT;

    private HashMap<Coordinates, BoardElement> elements;
    private Doctor doctor;

    public Board() {

    }

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

    public static int getBoardHeight() {
        return BOARD_HEIGHT;
    }

    public static void setBoardHeight(int boardHeight) {
        BOARD_HEIGHT = boardHeight;
    }

    public static int getBoardWidth() {
        return BOARD_WIDTH;
    }

    public static void setBoardWidth(int boardWidth) {
        BOARD_WIDTH = boardWidth;
    }
}
