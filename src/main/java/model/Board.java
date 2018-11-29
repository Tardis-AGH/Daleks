package model;

import java.util.HashMap;
import java.util.List;

public class Board {

    private static int BOARD_WIDTH = 20;
    private static int BOARD_HEIGHT = 20;

    private HashMap<Coordinates, BoardElement> elements;
    private Doctor doctor;

    public Board() {

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

    public List<DynamicBoardElement> getDynamicBoardElements() {
        //TODO
        return null;
    }

    public List<StaticBoardElement> getStaticBoardElements() {
        //TODO
        return null;
    }

    public void setWidth(int width){
        Board.BOARD_WIDTH=width;
    }

    public void setHeight(int height){
        Board.BOARD_HEIGHT=height;
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
