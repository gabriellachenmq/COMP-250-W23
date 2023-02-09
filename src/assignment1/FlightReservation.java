package assignment1;

public class FlightReservation extends Reservation{

    private Airport departure;
    private Airport arrival;

    public FlightReservation(String name, Airport departure, Airport arrival){
        super(name);
        if (arrival.equals(departure))
            throw new IllegalArgumentException("Departure and arrival airports cannot be the same.");
        this.arrival = arrival;
        this.departure = departure;
    }

    public int getCost(){
        double airportDistance = Airport.getDistance(this.arrival, this.departure);
        double gallonFuel = airportDistance / 167.52;
        double fuelCost = gallonFuel*124;
        int totalCost = (int) Math.ceil(fuelCost+this.arrival.getFees()+this.departure.getFees()+5375);
        return totalCost;

    }

    public boolean equals(Object obj) {
        if (!(obj instanceof FlightReservation)) {
            return false;
        }
        FlightReservation other = (FlightReservation) obj;
        return (this.departure.equals(other.departure) && this.arrival.equals(other.arrival) &&
                this.reservationName().equals(other.reservationName()));

    }

    public static void main(String[] args) {
        Airport airport1 = new Airport(44, 120, 100);
        Airport airport2 = new Airport(50, 112, 110);
        FlightReservation flightReservation1 = new FlightReservation("Alex", airport1, airport2);
        FlightReservation flightReservation2 = new FlightReservation("Alex", airport1, airport2);
        System.out.println(flightReservation1.equals(flightReservation2));


    }



}
