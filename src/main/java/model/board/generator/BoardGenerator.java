package model.board.generator;

import java.util.Random;
import java.util.stream.Stream;
import javafx.collections.FXCollections;
import javafx.collections.ObservableSet;
import model.board.Board;
import model.board.Coordinates;
import model.element.BoardElement;
import model.element.dynamicelement.Dalek;
import model.element.dynamicelement.Doctor;
import model.element.staticelement.Heart;
import model.element.staticelement.Teleporter;

/**
 * The type Board generator.
 */
public class BoardGenerator {

    private static final double HEART_RATIO = 0.01;
    private static final double TELEPORTER_RATIO = 0.01;
    private static final double DALEKS_RATIO = 1.5;
    private static final int DALEKS_OFFSET = 5;
    private final Random random;
    private final int boardHeight = 21;
    private final int boardWidth = 21;

    /**
     * Instantiates a new Board generator.
     *
     * @param random the random
     */
    public BoardGenerator(Random random) {
        this.random = random;
    }

    /**
     * Generate new board board.
     *
     * @param level the level
     *
     * @return the board
     */
    public Board generateNewBoard(int level) {
        final ObservableSet<BoardElement> elements = FXCollections.observableSet();
        final CoordinatesGenerator coordinatesGenerator =
                new CoordinatesGenerator(this.random, elements, boardWidth, boardHeight);
        final Doctor doctor = generateDoctor(coordinatesGenerator);
        final Board board = new Board(elements, doctor);
        board.getElements().add(doctor);
        populateWithDaleks(board, coordinatesGenerator, level);
        populateWithHearts(board, coordinatesGenerator);
        populateWithTeleporters(board, coordinatesGenerator);
        return board;
    }

    private Doctor generateDoctor(CoordinatesGenerator coordinatesGenerator) {
        return new Doctor(new Coordinates(boardWidth / 2, boardHeight / 2, boardWidth, boardHeight),
                coordinatesGenerator);
    }

    private void populateWithHearts(Board board, CoordinatesGenerator coordinatesGenerator) {
        Stream.iterate(0, n -> n + 1)
                .limit((int) (boardHeight * boardWidth * HEART_RATIO))
                .forEach(e -> board.getElements().add(new Heart(coordinatesGenerator.getRandomCoordinates())));
    }

    private void populateWithDaleks(Board board, CoordinatesGenerator coordinatesGenerator, int level) {
        Stream.iterate(1L, n -> n + 1)
                .limit(this.getDaleksNumber(level))
                .forEach(e -> board.getElements().add(new Dalek(coordinatesGenerator.getRandomCoordinates())));
    }

    private int getDaleksNumber(int level) {
        return (int) (DALEKS_RATIO * Math.sqrt(level - 1) + DALEKS_OFFSET);
    }

    private void populateWithTeleporters(Board board, CoordinatesGenerator coordinatesGenerator) {
        Stream.iterate(0, n -> n + 1)
                .limit((int) (boardHeight * boardWidth * TELEPORTER_RATIO))
                .forEach(e -> board.getElements().add(new Teleporter(coordinatesGenerator.getRandomCoordinates())));
    }
}
