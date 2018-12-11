package model.board;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableSet;
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
    private final Random generator;

    /**
     * Instantiates a new Board.
     *
     * @param doctor the doctor
     * @param boardWidth the board width
     * @param boardHeight the board height
     */
    public Board(Doctor doctor, int boardWidth, int boardHeight) {
        this.generator = new Random();
        this.boardWidth = boardWidth;
        this.boardHeight = boardHeight;
        this.elements = FXCollections.observableSet();
        //this.coordinatesGenerator = new CoordinatesGenerator(elements, boardWidth, boardHeight);
        this.doctor = doctor;
        elements.add(doctor);
    }

    /**
     * Gets daleks.
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
     * Gets elements.
     *
     * @return the elements
     */
    public ObservableSet<BoardElement> getElements() {
        return elements;
    }

    /**
     * Get new valid (not occupied) coordinates.
     *
     * @return new coordinates
     */
    public Coordinates getRandomCoordinates() {
        Coordinates newCoordinates;
        do {
            newCoordinates = new Coordinates(generator.nextInt(boardWidth), generator.nextInt(boardHeight), boardWidth,
                    boardHeight);
        } while (!isFieldEmpty(newCoordinates));
        return newCoordinates;
    }

    /**
     * Check if field determined by coordinates is not occupied by any dynamic element (dalek).
     *
     * @param coordinates coordinates to check
     *
     * @return true if given field is empty, false otherwise
     */
    private boolean isFieldEmpty(Coordinates coordinates) {
        for (BoardElement boardElement : elements) {
            if (boardElement.getCoordinates().equals(coordinates)) {
                return false;
            }
        }
        return true;
    }
}
