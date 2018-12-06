package model;

/**
 * The type Coordinates.
 */
public class Coordinates {

    private int x;
    private int y;

    /**
     * Instantiates a new Coordinates.
     *
     * @param x the x
     * @param y the y
     */
    public Coordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Instantiates a new Coordinates.
     *
     * @param coordinates the coordinates
     */
    public Coordinates(Coordinates coordinates) {
        this.x = coordinates.getX();
        this.y = coordinates.getY();
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
     * Sets y.
     *
     * @param y the y
     */
    public void setY(int y) {
        this.y = Math.min(Math.max(y, 0), Board.getBoardHeight() - 1);
    }

    /**
     * Gets x.
     *
     * @return the x
     */
    public int getX() {
        return x;
    }

    /**
     * Sets x.
     *
     * @param x the x
     */
    public void setX(int x) {
        this.x = Math.min(Math.max(x, 0), Board.getBoardWidth() - 1);
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
        return y * Board.getBoardWidth() + x;
    }

    /**
     * Add to x.
     *
     * @param deltaX the delta x
     */
    public void addToX(int deltaX) {
        setX(getX() + deltaX);
    }

    /**
     * Add to y.
     *
     * @param deltaY the delta y
     */
    public void addToY(int deltaY) {
        setY(getY() + deltaY);
    }

    @Override
    public String toString() {
        return "Coordinates{" + "x=" + x + ", y=" + y + '}';
    }
}
