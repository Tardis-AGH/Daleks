package model;

public class Coordinates {

    private final int x;
    private final int y;

    public Coordinates(int x, int y) {

        this.x = Math.min(Math.max(x, 0), Board.getBoardWidth() - 1);
        this.y = Math.min(Math.max(y, 0), Board.getBoardHeight() - 1);
    }

    public Coordinates getUpdated(Move move) {
        return new Coordinates(x + move.getDeltaX(), y + move.getDeltaY());
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

    public int getX() {
        return x;
    }

    @Override
    public String toString() {
        return "Coordinates{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
