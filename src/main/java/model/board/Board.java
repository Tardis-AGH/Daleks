package model.board;

import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
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
        this.doctor = doctor;
        elements.add(doctor);
    }

    private <T extends BoardElement> List<T> filterBoardElements(Class<T> boardElementType) {
        return elements.stream()
                .filter(boardElementType::isInstance)
                .map(boardElementType::cast)
                .collect(Collectors.toList());
    }

    /**
     * Gets daleks.
     *
     * @return the daleks
     */
    public List<Dalek> getDaleks() {
        return filterBoardElements(Dalek.class);
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
     * Get new valid (not occupied) coordinates.
     *
     * @return new coordinates
     */
    public Coordinates getRandomCoordinates() {
        final Set<Coordinates> occupiedCoordinates =
                elements.stream().map(BoardElement::getCoordinates).collect(Collectors.toSet());
        final List<Coordinates> availableCoordinates = IntStream.range(0, boardHeight * boardWidth)
                .mapToObj(e -> new Coordinates(e % boardWidth, e / boardWidth, boardWidth, boardHeight))
                .filter(e -> !occupiedCoordinates.contains(e))
                .collect(Collectors.toList());
        return availableCoordinates.get(generator.nextInt(availableCoordinates.size()));
    }
}
