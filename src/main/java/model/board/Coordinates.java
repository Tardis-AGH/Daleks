package model.board;

/**
 * The type Coordinates.
 */
public class Coordinates {

    private final int x;
    private final int y;
    private final int boardWidth;
    private final int boardHeight;

    /**
     * Instantiates a new Coordinates.
     *
     * @param x the x
     * @param y the y
     * @param boardWidth the board width
     * @param boardHeight the board height
     */
    public Coordinates(int x, int y, int boardWidth, int boardHeight) {
        this.boardWidth = boardWidth;
        this.boardHeight = boardHeight;

        this.x = Math.min(Math.max(x, 0), boardWidth - 1);
        this.y = Math.min(Math.max(y, 0), boardHeight - 1);
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
     * @param x the x
     * @param y the y
     *
     * @return the updated
     */
    public Coordinates getUpdated(int x, int y) {
        return new Coordinates(x, y, boardWidth, boardHeight);
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
        return y * boardHeight + x;
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
