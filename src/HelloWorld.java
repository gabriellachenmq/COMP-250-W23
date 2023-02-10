public class HelloWorld {

    public static void main(String[] args){
        System.out.println("hello");
        double a = circleArea(2.5);
        System.out.println(a);

    }


    public  static double circleArea(double radius){
        double area = Math.PI * Math.pow(radius, 2.0);
        return area;

    }

}
