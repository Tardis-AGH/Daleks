package model.board;

import java.util.List;
import java.util.stream.Collectors;
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

    /**
     * Instantiates a new Board.
     *
     * @param elements the elements
     * @param doctor the doctor
     */
    public Board(ObservableSet<BoardElement> elements, Doctor doctor) {
        this.elements = elements;
        this.doctor = doctor;
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
}
