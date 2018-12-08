package model;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import model.element.BoardElement;
import model.element.StaticBoardElement;
import model.element.dynamicelement.Dalek;
import model.element.dynamicelement.Doctor;

/**
 * The type Board.
 */
public class Board {

    private static int BOARD_WIDTH = 20;
    private static int BOARD_HEIGHT = 20;

    private HashMap<Coordinates, BoardElement> elements;
    private Doctor doctor;

    /**
     * Instantiates a new Board.
     */
    public Board() {

    }

    /**
     * Gets board height.
     *
     * @return the board height
     */
    public static int getBoardHeight() {
        return BOARD_HEIGHT;
    }

    /**
     * Sets board height.
     *
     * @param boardHeight the board height
     */
    public static void setBoardHeight(int boardHeight) {
        BOARD_HEIGHT = boardHeight;
    }

    /**
     * Gets board width.
     *
     * @return the board width
     */
    public static int getBoardWidth() {
        return BOARD_WIDTH;
    }

    /**
     * Sets board width.
     *
     * @param boardWidth the board width
     */
    public static void setBoardWidth(int boardWidth) {
        BOARD_WIDTH = boardWidth;
    }

    /**
     * Gets daleks.
     *
     * @param elements the elements
     *
     * @return the daleks
     */
    public List<Dalek> getDaleks(Collection<BoardElement> elements) {
        return elements.stream().filter(e -> e instanceof Dalek).map(e -> (Dalek) e).collect(Collectors.toList());
    }

    /**
     * Gets static board elements.
     *
     * @param elements the elements
     *
     * @return the static board elements
     */
    public List<StaticBoardElement> getStaticBoardElements(Collection<BoardElement> elements) {
        return elements.stream()
                .filter(e -> e instanceof StaticBoardElement)
                .map(e -> (StaticBoardElement) e)
                .collect(Collectors.toList());
    }

    /**
     * Gets doctor.
     *
     * @return the doctor
     */
    public Doctor getDoctor() {
        return doctor;
    }

    /**
     * Sets doctor.
     *
     * @param doctor the doctor
     */
    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    /**
     * Gets elements.
     *
     * @return the elements
     */
    public HashMap<Coordinates, BoardElement> getElements() {
        return elements;
    }

    /**
     * Sets elements.
     *
     * @param elements the elements
     */
    public void setElements(HashMap<Coordinates, BoardElement> elements) {
        this.elements = elements;
    }
}
