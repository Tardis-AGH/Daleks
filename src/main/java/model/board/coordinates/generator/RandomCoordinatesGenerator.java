package model.board.coordinates.generator;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import model.board.Board;
import model.board.coordinates.Coordinates;
import model.element.BoardElement;

/**
 * The type Random coordinates generator.
 */
public class RandomCoordinatesGenerator {

    private final Random generator;
    private final Board board;

    /**
     * Instantiates a new Random coordinates generator.
     *
     * @param board the board
     */
    public RandomCoordinatesGenerator(Board board) {
        this.generator = new Random();
        this.board = board;
    }

    /**
     * Get new valid (not occupied) coordinates.
     *
     * @return new coordinates
     */
    public Coordinates getRandomCoordinates() {
        final int boardWidth = board.getWidth();
        final int boardHeight = board.getHeight();
        final List<Coordinates> occupiedCoordinates =
                board.getElements().stream().map(BoardElement::getCoordinates).collect(Collectors.toList());
        final List<Coordinates> availableCoordinates = IntStream.range(0, boardHeight * boardWidth)
                .mapToObj(xy -> new Coordinates(xy % boardWidth, xy / boardWidth, boardWidth, boardHeight))
                .collect(Collectors.toList());
        availableCoordinates.removeAll(occupiedCoordinates);
        return availableCoordinates.get(generator.nextInt(availableCoordinates.size()));
    }
}
