package model.board.coordinates.generator;

import java.util.List;
import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import model.board.Board;
import model.board.coordinates.Coordinates;
import model.element.BoardElement;
import model.element.dynamicelement.Dalek;

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
     * Gets random coordinates without any extra constraints in terms of distance from the enemies.
     *
     * @return the random coordinates
     */
    public Coordinates getRandomCoordinates() {
        return getRandomCoordinates(1);
    }

    /**
     * Get new valid (not occupied) coordinates.
     *
     * @param distance the distance from daleks.
     *
     * @return new coordinates
     */
    public Coordinates getRandomCoordinates(int distance) {
        final int boardWidth = board.getWidth();
        final int boardHeight = board.getHeight();

        final Supplier<Stream<Coordinates>> occupiedCoordinates =
                () -> Stream.concat(board.getElements().stream().map(BoardElement::getCoordinates),
                        getDalekNeighbors(board.getDaleks(), distance)).distinct();

        final Supplier<Stream<Coordinates>> allCoordinates = () -> IntStream.range(0, boardHeight * boardWidth)
                .mapToObj(xy -> new Coordinates(xy % boardWidth, xy / boardWidth, boardWidth, boardHeight));

        final List<Coordinates> availableCoordinates = allCoordinates.get()
                .filter(coordinates -> occupiedCoordinates.get().noneMatch(other -> other.equals(coordinates)))
                .collect(Collectors.toList());

        if (availableCoordinates.size() == 0) {
            throw new RuntimeException("All teleportation locations are occupied");
        }
        return availableCoordinates.get(generator.nextInt(availableCoordinates.size()));
    }

    private Stream<Coordinates> getDalekNeighbors(List<Dalek> elements, int distance) {
        return elements.stream()
                .flatMap(element -> IntStream.range(0, (int) Math.pow(2 * distance + 1, 2))
                        .mapToObj(coordinates -> element.getCoordinates()
                                .getUpdated(
                                        element.getCoordinates().getX() + coordinates % (2 * distance + 1) - distance,
                                        element.getCoordinates().getY() + coordinates / (2 * distance + 1) - distance))
                        .filter(coordinates -> !coordinates.equals(element.getCoordinates())))
                .distinct();
    }

}
