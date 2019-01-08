package model.board.coordinates;

import model.board.Move;

import java.util.Objects;

/**
 * The type Coordinates.
 */
public class Coordinates {

    private final int x;
    private final int y;
    private final int boardWidth;
    private final int boardHeight;

    /**
     * Instantiates new Coordinates.
     *
     * @param xCoordinate the x
     * @param yCoordinate the y
     * @param boardWidth the board width
     * @param boardHeight the board height
     */
    public Coordinates(int xCoordinate, int yCoordinate, int boardWidth, int boardHeight) {
        this.boardWidth = boardWidth;
        this.boardHeight = boardHeight;

        this.x = Math.min(Math.max(xCoordinate, 0), boardWidth - 1);
        this.y = Math.min(Math.max(yCoordinate, 0), boardHeight - 1);
    }

    /**
     * Gets updated.
     *
     * @param move the move
     *
     * @return the updated
     */
    public Coordinates getUpdated(Move move) {
        return new Coordinates(x + move.getDeltaX(), y + move.getDeltaY(), boardWidth, boardHeight);
    }

    /**
     * Gets updated.
     *
     * @param xCoordinate the x
     * @param yCoordinate the y
     *
     * @return the updated
     */
    public Coordinates getUpdated(int xCoordinate, int yCoordinate) {
        return new Coordinates(xCoordinate, yCoordinate, boardWidth, boardHeight);
    }

    public int distance(Coordinates other) {
        return Math.abs(this.x - other.getX()) + Math.abs(this.y - other.getY());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final Coordinates that = (Coordinates) o;
        return x == that.x && y == that.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    /**
     * Gets y.
     *
     * @return the y
     */
    public int getY() {
        return y;
    }

    /**
     * Gets x.
     *
     * @return the x
     */
    public int getX() {
        return x;
    }

    @Override
    public String toString() {
        return "Coordinates{" + "x=" + x + ", y=" + y + '}';
    }
}
