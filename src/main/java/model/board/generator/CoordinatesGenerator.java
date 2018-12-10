package model.board.generator;

import java.util.Random;
import java.util.Set;
import model.board.Coordinates;
import model.element.BoardElement;

/**
 * The type Coordinates generator.
 */
public class CoordinatesGenerator {

    private final Set<BoardElement> elements;
    private final Integer boardHeight;
    private final Integer boardWidth;
    private final Random generator;

    /**
     * Instantiates a new Coordinates generator.
     *
     * @param generator the generator
     * @param elements the elements
     * @param boardWidth the board width
     * @param boardHeight the board height
     */
    public CoordinatesGenerator(Random generator, Set<BoardElement> elements, int boardWidth, int boardHeight) {
        this.elements = elements;
        this.boardHeight = boardHeight;
        this.boardWidth = boardWidth;
        this.generator = generator;
    }

    /**
     * Get new valid (not occupied) coordinates.
     *
     * @return new coordinates
     */
    public Coordinates getRandomCoordinates() {
        Coordinates newCoordinates;
        do {
            newCoordinates = new Coordinates(generator.nextInt(boardWidth), generator.nextInt(boardHeight), boardWidth,
                    boardHeight);
        } while (!isFieldEmpty(newCoordinates, elements));
        return newCoordinates;
    }

    /**
     * Check if field determined by coordinates is not occupied by any dynamic element (dalek).
     *
     * @param coordinates coordinates to check
     * @param elements set of elements with possible collision
     *
     * @return true if given field is empty, false otherwise
     */
    private boolean isFieldEmpty(Coordinates coordinates, Set<BoardElement> elements) {
        for (BoardElement boardElement : elements) {
            if (boardElement.getCoordinates().equals(coordinates)) {
                return false;
            }
        }
        return true;
    }
}
