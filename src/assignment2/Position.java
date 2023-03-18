package assignment2;

public class Position {

    private int xCor;
    private int yCor;

    public Position(int x, int y){
        this.xCor = x;
        this.yCor = y;
    }

    public Position(Position p){
        this.xCor = p.xCor;
        this.yCor = p.yCor;

    }

    public void reset(int x, int y){
        this.xCor = x;
        this.yCor = y;
    }

    public void  reset(Position p){
        this.xCor = p.xCor;
        this.yCor = p.yCor;
    }

    public static int getDistance(Position a, Position b){
        int dX = Math.abs(a.xCor - b.xCor);
        int dY = Math.abs(a.yCor - b.yCor);
        return dX + dY;

    }

    public int getX(){
        return this.xCor;
    }

    public int getY(){
        return this.yCor;
    }

    public void moveWest(){
        this.xCor--;
    }

    public void moveEast(){
        this.xCor++;
    }

    public void moveNorth(){
        this.yCor--;
    }

    public void moveSouth(){
        this.yCor++;
    }

    public boolean equals(Object obj){
        if (!(obj instanceof Position)) {
            return false;
        }
        Position otherPos = (Position) obj;
        return (this.xCor == otherPos.xCor && this.yCor == otherPos.yCor);
    }
}
