package assignment1;

public class Customer {

    private String customerName;
    private int balance;
    private Basket reservations;

    public Customer(String name, int iniBalance){

        this.customerName = name;
        this.balance = iniBalance;
        this.reservations = new Basket();
    }

    public String getName(){
        return this.customerName;
    }

    public int getBalance(){
        return this.balance;
    }

    public Basket getBasket(){
        return this.reservations;
    }

    public int addFunds(int amount){
        if (amount < 0)
            throw new IllegalArgumentException("Negative amount of funds cannot be added.");
        this.balance+=amount;
        return this.balance;
    }

    public int addToBasket(Reservation r){
        if (this.customerName.equals(r.reservationName()))
            return this.reservations.add(r);
        throw new IllegalArgumentException("Add reservation to the basket was not successful");

    }

    public int addToBasket(Hotel name, String roomType, int num, boolean breakfast){
        if (!breakfast) {
            Reservation hotel = new HotelReservation(this.customerName, name, roomType, num);
            return this.reservations.add(hotel);
        }
        Reservation bnb = new BnBReservation(this.customerName, name, roomType, num);
        return this.reservations.add(bnb);
    }

    public int addToBasket(Airport arrival, Airport departure){
        if (arrival.equals(departure))
            return this.reservations.getNumOfReservations();
        Reservation airport = new FlightReservation(this.customerName, arrival, departure);
        return this.reservations.add(airport);

    }

    public boolean removeFromBasket(Reservation r){
        return this.reservations.remove(r);
    }

    public int checkOut(){
        if (this.balance < this.reservations.getTotalCost())
            throw new IllegalStateException("Not enough balance in your account");
        this.balance = this.balance - this.reservations.getTotalCost();
        this.reservations.clear();
        return this.balance;

    }


}
