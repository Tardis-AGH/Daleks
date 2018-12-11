package model.board.generator;

import java.util.stream.Stream;
import model.board.Board;
import model.board.Coordinates;
import model.element.dynamicelement.Dalek;
import model.element.dynamicelement.Doctor;
import model.element.staticelement.Heart;
import model.element.staticelement.Teleporter;

/**
 * The type Board generator.
 */
public class RandomBoardGenerator implements BoardGenerator {

    private static final double HEART_RATIO = 0.01;
    private static final double TELEPORTER_RATIO = 0.01;
    private static final double DALEKS_RATIO = 1.5;
    private static final int DALEKS_OFFSET = 5;
    private static final int boardHeight = 21;
    private static final int boardWidth = 21;

    /**
     * Generate new board board.
     *
     * @param level the level
     *
     * @return the board
     */
    public Board generateNewBoard(int level) {
        final Doctor doctor = generateDoctor();
        final Board board = new Board(doctor, boardWidth, boardHeight);
        populateWithDaleks(board, level);
        populateWithHearts(board);
        populateWithTeleporters(board);
        return board;
    }

    private Doctor generateDoctor() {
        return new Doctor(new Coordinates(boardWidth / 2, boardHeight / 2, boardWidth, boardHeight));
    }

    private void populateWithHearts(Board board) {
        Stream.iterate(0, n -> n + 1)
                .limit((int) (boardHeight * boardWidth * HEART_RATIO))
                .forEach(e -> board.getElements().add(new Heart(board.getRandomCoordinates())));
    }

    private void populateWithDaleks(Board board, int level) {
        Stream.iterate(1L, n -> n + 1)
                .limit(this.getDaleksNumber(level))
                .forEach(e -> board.getElements().add(new Dalek(board.getRandomCoordinates())));
    }

    private int getDaleksNumber(int level) {
        return (int) (DALEKS_RATIO * Math.sqrt(level - 1) + DALEKS_OFFSET);
    }

    private void populateWithTeleporters(Board board) {
        Stream.iterate(0, n -> n + 1)
                .limit((int) (boardHeight * boardWidth * TELEPORTER_RATIO))
                .forEach(e -> board.getElements().add(new Teleporter(board.getRandomCoordinates())));
    }

    public int getBoardHeight() {
        return boardHeight;
    }

    public int getBoardWidth() {
        return boardWidth;
    }
}
