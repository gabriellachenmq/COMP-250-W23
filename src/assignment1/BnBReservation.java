package assignment1;

public class BnBReservation extends HotelReservation{

    public BnBReservation(String name, Hotel hotelName, String roomType, int num){
        super(name, hotelName, roomType, num);
    }

    public int getCost(){

        return super.getCost() + (1000 * super.getNumOfNights());
    }
}
