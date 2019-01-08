package model.board;

/**
 * The enum Move.
 */
public enum Move {
    /**
     * Wait move.
     */
    WAIT(0, 0),
    /**
     * Up move.
     */
    UP(0, -1),
    /**
     * Upper right move.
     */
    UPPER_RIGHT(1, -1),
    /**
     * Right move.
     */
    RIGHT(1, 0),
    /**
     * Lower right move.
     */
    LOWER_RIGHT(1, 1),
    /**
     * Down move.
     */
    DOWN(0, 1),
    /**
     * Lower left move.
     */
    LOWER_LEFT(-1, 1),
    /**
     * Left move.
     */
    LEFT(-1, 0),
    /**
     * Upper left move.
     */
    UPPER_LEFT(-1, -1),
    /**
     * Teleport move.
     */
    TELEPORT(0, 0),
    /**
     * Bomb move.
     */
    BOMB(0, 0);

    private final int deltaX;
    private final int deltaY;

    Move(int deltaX, int deltaY) {
        this.deltaX = deltaX;
        this.deltaY = deltaY;
    }

    /**
     * Gets delta x.
     *
     * @return the delta x
     */
    public int getDeltaX() {
        return deltaX;
    }

    /**
     * Gets delta y.
     *
     * @return the delta y
     */
    public int getDeltaY() {
        return deltaY;
    }
}
