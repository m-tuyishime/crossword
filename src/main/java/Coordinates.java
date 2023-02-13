public class Coordinates {
    private int x;
    private int y;

    public Coordinates(int x, int y) {
        this.x = positiveCheck(x);
        this.y = positiveCheck(y);
    }

    private static int positiveCheck(int coordinate) {
        if (coordinate < 0) 
            throw new IllegalArgumentException("coordinates must be positive");
        return coordinate;
    }

    @Override
    public int hashCode() {
        return x + y * (x+y);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Coordinates))
        return false;
        Coordinates other = (Coordinates) obj;
        return x == other.x && y == other.y;
    }
}
