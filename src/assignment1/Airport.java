package assignment1;

public class Airport {
    private int x_cor;
    private int y_cor;
    private int fees;

    public Airport(int x, int y, int f){
        this.x_cor = x;
        this.y_cor = y;
        this.fees = f;
    }

    public int getFees(){
        return this.fees;
    }

    public static int getDistance(Airport a, Airport b){

        double x_distance = Math.pow(a.x_cor-b.x_cor, 2);
        double y_distance = Math.pow(a.y_cor-b.y_cor, 2);
        double distance = (double)Math.sqrt(x_distance+y_distance);
        return (int)Math.ceil(distance);
    }
     public static void main(String[] args){
        Airport a = new Airport(44, 120, 100);
        Airport b = new Airport(55, 113, 100);
        System.out.println(getDistance(a,b));
    }
}