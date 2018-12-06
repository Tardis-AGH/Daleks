package model;

import javafx.beans.property.SimpleSetProperty;
import javafx.collections.ObservableSet;
import model.element.BoardElement;
import model.element.StaticBoardElement;
import model.element.dynamicelement.Dalek;
import model.element.dynamicelement.Doctor;

import java.util.List;
import java.util.stream.Collectors;

/**
 * The type Board.
 */
public class Board {

    private static int BOARD_WIDTH = 20;
    private static int BOARD_HEIGHT = 20;

    private final ObservableSet<BoardElement> elements = new SimpleSetProperty<>();
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
     *
     * @return the daleks
     */
    public List<Dalek> getDaleks() {
        return elements.stream().filter(e -> e instanceof Dalek).map(e -> (Dalek) e).collect(Collectors.toList());
    }

    /**
     * Gets static board elements.
     *
     * @return the static board elements
     */
    public List<StaticBoardElement> getStaticBoardElements() {
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
    public ObservableSet<BoardElement> getElements() {
        return elements;
    }
}
