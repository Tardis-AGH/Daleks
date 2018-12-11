package model.board.generator;

import javafx.collections.ObservableSet;
import model.board.Board;
import model.element.BoardElement;
import model.element.dynamicelement.Doctor;

public class TestBoardGenerator implements BoardGenerator {

    private ObservableSet<BoardElement> elements;
    private Doctor doctor;
    private int boardWidth;
    private int boardHeight;

    public TestBoardGenerator(ObservableSet<BoardElement> elements, Doctor doctor) {
        this.elements = elements;
        this.doctor = doctor;
        this.boardWidth = 10;
        this.boardHeight = 10;
    }

    public TestBoardGenerator(ObservableSet<BoardElement> elements, Doctor doctor, int boardWidth, int boardHeight) {
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