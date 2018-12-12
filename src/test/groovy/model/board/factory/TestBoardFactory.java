package model.board.factory;

import javafx.collections.ObservableSet;
import model.board.Board;
import model.element.BoardElement;
import model.element.dynamicelement.Doctor;

public class TestBoardFactory implements BoardFactory {

    private final ObservableSet<BoardElement> elements;
    private final Doctor doctor;
    private final int boardWidth;
    private final int boardHeight;

    public TestBoardFactory(ObservableSet<BoardElement> elements, Doctor doctor) {
        this.elements = elements;
        this.doctor = doctor;
        this.boardWidth = 10;
        this.boardHeight = 10;
    }

    public TestBoardFactory(ObservableSet<BoardElement> elements, Doctor doctor, int boardWidth, int boardHeight) {
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