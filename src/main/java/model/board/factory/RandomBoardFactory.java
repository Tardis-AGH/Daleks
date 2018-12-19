package model.board.factory;

import java.util.stream.Stream;
import model.board.Board;
import model.board.coordinates.Coordinates;
import model.element.dynamicelement.Dalek;
import model.element.dynamicelement.Doctor;
import model.element.staticelement.Heart;
import model.element.staticelement.Teleporter;

/**
 * The type random Board factory.
 */
public class RandomBoardFactory implements BoardFactory {

    private static final double HEART_RATIO = 0.01;
    private static final double TELEPORTER_RATIO = 0.01;
    private static final double DALEKS_RATIO = 1.5;
    private static final int DALEKS_OFFSET = 5;
    private static final int BOARD_HEIGHT = 21;
    private static final int BOARD_WIDTH = 21;

    /**
     * Generate new a new board randomly based on constants and the player's level.
     *
     * @param level the level
     *
     * @return the board
     */
    public Board generateNewBoard(int level) {
        final Doctor doctor = generateDoctor();
        final Board board = new Board(doctor, BOARD_WIDTH, BOARD_HEIGHT);
        populateWithDaleks(board, level);
        populateWithHearts(board);
        populateWithTeleporters(board);
        return board;
    }

    private Doctor generateDoctor() {
        return new Doctor(new Coordinates(BOARD_WIDTH / 2, BOARD_HEIGHT / 2, BOARD_WIDTH, BOARD_HEIGHT));
    }

    private void populateWithHearts(Board board) {
        Stream.iterate(0, n -> n + 1)
                .limit((int) (BOARD_HEIGHT * BOARD_WIDTH * HEART_RATIO))
                .forEach(
                        e -> board.getElements().add(new Heart(board.getCoordinateGenerator().getRandomCoordinates())));
    }

    private void populateWithDaleks(Board board, int level) {
        Stream.iterate(1L, n -> n + 1)
                .limit(this.getDaleksNumber(level))
                .forEach(
                        e -> board.getElements().add(new Dalek(board.getCoordinateGenerator().getRandomCoordinates())));
    }

    private int getDaleksNumber(int level) {
        return (int) (DALEKS_RATIO * Math.sqrt(level - 1) + DALEKS_OFFSET);
    }

    private void populateWithTeleporters(Board board) {
        Stream.iterate(0, n -> n + 1)
                .limit((int) (BOARD_HEIGHT * BOARD_WIDTH * TELEPORTER_RATIO))
                .forEach(e -> board.getElements()
                        .add(new Teleporter(board.getCoordinateGenerator().getRandomCoordinates())));
    }

    public int getBoardHeight() {
        return BOARD_HEIGHT;
    }

    public int getBoardWidth() {
        return BOARD_WIDTH;
    }
}
