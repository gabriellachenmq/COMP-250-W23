package assignment2;

public class Caterpillar extends MyDoublyLinkedList<Position>{
    private int size;

    public Caterpillar() {
        super();
        this.size = 1;
        this.add(new Position(7,7));
    }

    public Position getHead() {
        return this.peekFirst();
    }

    public void eat(Position pos) {
        Position head = this.getHead();
        if (!isAdjacent(pos)) {
            throw new IllegalArgumentException("The input position is not adjacent to the current head position.");
        }
        this.addFirst(pos);
        this.size++;
    }
    public void move(Position pos) {
        Position head = this.getHead();
        if (!isAdjacent(pos)) {
            throw new IllegalArgumentException("The input position is not adjacent to the current head position.");
        }
        this.addFirst(pos);

        this.removeLast();
    }
    public boolean selfCollision(Position pos) {
        for (Position p : this) {
            if (p.equals(pos)) {
                return true;
            }
        }
        return false;
    }
    private boolean isAdjacent(Position other) {
        int dx = Math.abs(this.peekFirst().getX()- other.getX());
        int dy = Math.abs(this.peekFirst().getY() - other.getY());
        return (dx == 1 && dy == 0) || (dx == 0 && dy == 1);
    }

}
