package model;

public class Coordinates {

    private final int BOARD_WIDTH;
    private int x;
    private int y;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinates that = (Coordinates) o;
        return x == that.x &&
                y == that.y;
    }

    @Override
    public int hashCode() {
        return y * BOARD_WIDTH + x;
    }

    public Coordinates(int x, int y, int boardWidth) {
        BOARD_WIDTH = boardWidth;
        this.x = x;
        this.y = y;
    }

    public int getY() {
        return y;
    }

    void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    void setX(int x) {
        this.x = x;
    }

    public int getBoardWidth() {
        return BOARD_WIDTH;
    }

}
