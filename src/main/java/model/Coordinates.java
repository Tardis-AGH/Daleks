package model;

public class Coordinates {

    private int x;
    private int y;

    public Coordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Coordinates(Coordinates coordinates) {
        this.x = coordinates.getX();
        this.y = coordinates.getY();
    }

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
        return y * Board.getBoardWidth() + x;
    }

    public int getY() {
        return y;
    }

    void setY(int y) {
        this.y = Math.min(Math.max(y, 0), Board.getBoardHeight() - 1);
    }

    public int getX() {
        return x;
    }

    void setX(int x) {
        this.x = Math.min(Math.max(x, 0), Board.getBoardWidth() - 1);
    }

}
