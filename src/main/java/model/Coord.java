package model;

public class Coord {

    public final int x;
    public final int y;

    public Coord(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Coord plus(int sx, int sy) {
        return new Coord(x + sx, y + sy);
    }
}
