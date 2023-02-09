package assignment1;

public class HotelReservation extends Reservation{

    private Hotel hotelChosen;
    private String reserveType;
    private int numOfNights;
    private int pricePerNight;

    public HotelReservation(String name, Hotel hotelName, String roomType, int num){
        super(name);
        this.hotelChosen = hotelName;
        this.reserveType = roomType;
        this.numOfNights = num;
        this.pricePerNight = this.hotelChosen.reserveRoom(this.reserveType);

    }

    public int getNumOfNights(){
        return this.numOfNights;
    }

    public int getCost(){
        return (this.numOfNights * this.pricePerNight);
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof HotelReservation)) {
            return false;
        }
        HotelReservation other = (HotelReservation) obj;
        return (this.hotelChosen.equals(other.hotelChosen) && this.reserveType.equals(other.reserveType) &&
                this.numOfNights == (other.numOfNights) && this.getCost() == other.getCost() &&
                this.reservationName().equals(other.reservationName()));

    }

    public static void main(String[] args) {
        Room[] rooms = {new Room("double"), new Room("king")};
        Hotel hotel1 = new Hotel("Hotel1", rooms);
        HotelReservation hotelReservation1 = new HotelReservation("Alex", hotel1, "queen", 2);
        HotelReservation hotelReservation2 = new HotelReservation("Bob", hotel1, "king", 1);
        System.out.println(hotelReservation1);
    }



}
