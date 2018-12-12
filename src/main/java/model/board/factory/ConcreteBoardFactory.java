package model.board.factory;

import javafx.collections.ObservableSet;
import model.board.Board;
import model.element.BoardElement;
import model.element.dynamicelement.Doctor;

/**
 * The type Concrete board factory.
 */
public class ConcreteBoardFactory implements BoardFactory {

    private final ObservableSet<BoardElement> elements;
    private final Doctor doctor;
    private final int boardWidth;
    private final int boardHeight;

    /**
     * Instantiates a new Concrete board factory.
     *
     * @param elements the elements
     * @param doctor the doctor
     * @param boardWidth the board width
     * @param boardHeight the board height
     */
    public ConcreteBoardFactory(ObservableSet<BoardElement> elements, Doctor doctor, int boardWidth, int boardHeight) {
        this.elements = elements;
        this.doctor = doctor;
        this.boardWidth = boardWidth;
        this.boardHeight = boardHeight;
    }

    @Override
    public Board generateNewBoard(int level) {
        final Board board = new Board(doctor, boardWidth, boardHeight);
        for (BoardElement boardElement : elements) {
            board.getElements().add(boardElement);
        }
        board.getElements().add(doctor);
        return board;
    }

    @Override
    public int getBoardHeight() {
        return boardHeight;
    }

    @Override
    public int getBoardWidth() {
        return boardWidth;
    }
}