package model.board;

import java.util.List;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableSet;
import model.board.coordinates.generator.RandomCoordinatesGenerator;
import model.element.BoardElement;
import model.element.StaticBoardElement;
import model.element.dynamicelement.Dalek;
import model.element.dynamicelement.Doctor;

/**
 * The type Board.
 */
public class Board {

    private final ObservableSet<BoardElement> elements;
    private final Doctor doctor;
    private final Integer boardHeight;
    private final Integer boardWidth;
    private final RandomCoordinatesGenerator coordinateGenerator;

    /**
     * Instantiates a new Board.
     *
     * @param doctor the doctor
     * @param boardWidth the board width
     * @param boardHeight the board height
     */
    public Board(Doctor doctor, int boardWidth, int boardHeight) {
        this.boardWidth = boardWidth;
        this.boardHeight = boardHeight;
        this.elements = FXCollections.observableSet();
        this.doctor = doctor;
        elements.add(doctor);
        this.coordinateGenerator = new RandomCoordinatesGenerator(this);
    }

    /**
     * Gets daleks.
     *
     * @return the daleks
     */
    public List<Dalek> getDaleks() {
        return filterBoardElements(Dalek.class);
    }

    private <T extends BoardElement> List<T> filterBoardElements(Class<T> boardElementType) {
        return elements.stream()
                .filter(boardElementType::isInstance)
                .map(boardElementType::cast)
                .collect(Collectors.toList());
    }

    /**
     * Gets static board elements.
     *
     * @return the static board elements
     */
    public List<StaticBoardElement> getStaticBoardElements() {
        return filterBoardElements(StaticBoardElement.class);
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
     * Gets elements.
     *
     * @return the elements
     */
    public ObservableSet<BoardElement> getElements() {
        return elements;
    }

    /**
     * Gets width.
     *
     * @return the width
     */
    public Integer getWidth() {
        return boardWidth;
    }

    /**
     * Gets height.
     *
     * @return the height
     */
    public Integer getHeight() {
        return boardHeight;
    }

    /**
     * Gets coordinate generator.
     *
     * @return the coordinate generator
     */
    public RandomCoordinatesGenerator getCoordinateGenerator() {
        return coordinateGenerator;
    }
}
