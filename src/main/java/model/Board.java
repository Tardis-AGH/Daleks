package model;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class Board {

    private static int BOARD_WIDTH = 20;
    private static int BOARD_HEIGHT = 20;

    private HashMap<Coordinates, BoardElement> elements;
    private Doctor doctor;

    public Board() {

    }

    public void setElements(HashMap<Coordinates, BoardElement> elements) {
        this.elements = elements;
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

    public List<Dalek> getDaleks(Collection<BoardElement> elements) {
        return elements
                .stream()
                .filter(e -> e instanceof Dalek)
                .map(e -> (Dalek) e)
                .collect(Collectors.toList());
    }

    public List<StaticBoardElement> getStaticBoardElements(Collection<BoardElement> elements) {
        return elements
                .stream()
                .filter(e -> e instanceof StaticBoardElement)
                .map(e -> (StaticBoardElement) e)
                .collect(Collectors.toList());
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
