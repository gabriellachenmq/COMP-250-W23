package assignment2;

public class Region {
    private int minX;
    private int minY;
    private int maxX;
    private int maxY;

    public Region(int minX, int minY, int maxX, int maxY) {
        this.minX = minX;
        this.minY = minY;
        this.maxX = maxX;
        this.maxY = maxY;
    }
    public boolean contains(Position pos) {
        int x = pos.getX();
        int y = pos.getY();
        return x >= minX && x <= maxX && y >= minY && y <= maxY;
    }

}
