package assignment1;

public class Airport {
    private int xCor;
    private int yCor;
    private int fees;

    public Airport(int x, int y, int f){
        this.xCor = x;
        this.yCor = y;
        this.fees = f;
    }

    public int getFees(){
        return this.fees;
    }

    public static int getDistance(Airport a, Airport b){

        double xDistance = Math.pow(a.xCor-b.xCor, 2);
        double yDistance = Math.pow(a.yCor-b.yCor, 2);
        double distance = (double)Math.sqrt(xDistance+yDistance);
        return (int)Math.ceil(distance);
    }
     public static void main(String[] args){
        Airport a = new Airport(44, 120, 100);
        Airport b = new Airport(55, 113, 100);
        System.out.println(getDistance(a,b));
    }
}