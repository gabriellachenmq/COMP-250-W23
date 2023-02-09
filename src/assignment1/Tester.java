package assignment1;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.lang.reflect.Field;
import java.util.Arrays;


public class Tester {
}
class HotelTest {       // 7 points
    @Test
    @Tag("score:2")
    @DisplayName("Hotel Constructor Test1")
    void deepCopyConstructor_Test1() throws IllegalAccessException {
        Room[] rooms = {new Room("double"), new Room("queen"), new Room("king")};
        Hotel hotel = new Hotel("Hotel1", rooms);

        Room[] roomsCopy = new Room[rooms.length];
        String name = "";

        Field[] fields = Hotel.class.getDeclaredFields();
        for (Field field : fields) {
            if (field.getType() == Room[].class) {
                field.setAccessible(true);
                roomsCopy = (Room[]) field.get(hotel);
            } else if (field.getType() == String.class) {
                field.setAccessible(true);
                name = (String) field.get(hotel);
            }
        }

        assertFalse(Arrays.deepEquals(rooms, roomsCopy),
                "Hotel: deep copy constructor did not create a deep copy of the input rooms array");
        assertEquals("Hotel1", name,
                "Hotel: Constructor did not set the name correctly");
    }

    @Test
    @Tag("score:2")
    @DisplayName("Hotel reserveRoom() Test1")
    void reserveRoom_Test1() {
        Room[] rooms = {new Room("double")};
        Hotel hotel1 = new Hotel("Hotel1", rooms);
        assertEquals(9000, hotel1.reserveRoom("double"),
                "Hotel: reserveRoom() did not return the correct price of the room after reserving it");
    }

    @Test
    @Tag("score:1")
    @DisplayName("Hotel reserveRoom() Test2")
    void reserveRoom_Test2() {
        Room[] rooms = {new Room("double")};
        Hotel hotel1 = new Hotel("Hotel1", rooms);
        assertThrows(IllegalArgumentException.class, () -> hotel1.reserveRoom("king"),
                "Hotel: reserveRoom() did not throw an exception for an invalid room type");
    }
    @Test
    @Tag ("score:1") @DisplayName("Hotel cancelRoom() Test1")
    void cancelRoom_Test1() {
        Room[] rooms = {new Room("double")};
        Hotel hotel1 = new Hotel("Hotel1", rooms);
        hotel1.reserveRoom("double");
        assertTrue(hotel1.cancelRoom("double"),
                "Hotel: cancelRoom() did not return the correct value");
    }

    @Test
    @Tag ("score:1") @DisplayName("Hotel cancelRoom() Test2")
    void cancelRoom_Test2() {
        Room[] rooms = {new Room("double")};
        Hotel hotel1 = new Hotel("Hotel1", rooms);
        assertFalse(hotel1.cancelRoom("king"),
                "Hotel: cancelRoom() did not return the correct value");
    }

    // empty room listing
    @Test
    @Tag ("score:1") @DisplayName("Hotel cancelRoom() Test3")
    void cancelRoom_Test3() {
        Room[] rooms = {};
        Hotel hotel1 = new Hotel("Hotel1", rooms);
        assertFalse(hotel1.cancelRoom("king"),
                "Hotel: cancelRoom() did not return the correct value");
    }

}
class FlightReservationTest {   // 3 points

    @Test
    @Tag("score:1")
    @DisplayName("FlightReservation getCost() Test1")
    void getCost() {
        Airport airport1 = new Airport(44, 120, 100);
        Airport airport2 = new Airport(50, 112, 110);
        FlightReservation flightReservation1 = new FlightReservation("Alex", airport1, airport2);
        assertEquals(5593, flightReservation1.getCost(),
                "FlightReservation: getCost() returns the wrong cost.");
    }

    @Test
    @Tag("score:1")
    @DisplayName("FlightReservation equals() Test1")
    void testEquals1() {
        Airport airport1 = new Airport(44, 120, 100);
        Airport airport2 = new Airport(50, 112, 110);

        FlightReservation flightReservation1 = new FlightReservation("Alex", airport1, airport2);
        FlightReservation flightReservation2 = flightReservation1;

        assertTrue(flightReservation1.equals(flightReservation2),
                "FlightReservation: equals() returns the wrong value");
    }
    @Test
    @Tag("score:1") @DisplayName("FlightReservation equals() Test2")
    void testEquals2() {
        Airport airport1 = new Airport(44, 120, 100);
        Airport airport2 = new Airport(50, 112, 110);

        FlightReservation flightReservation1 = new FlightReservation("Alex", airport1, airport2);
        FlightReservation flightReservation2 = new FlightReservation("Alex", airport2, airport1);

        assertFalse(flightReservation1.equals(flightReservation2),
                "FlightReservation: equals() returns the wrong value.");
    }


    // check equals(null)
    @Test
    @Tag("score:1") @DisplayName("FlightReservation equals() Test4")
    void testEquals4() {
        Airport airport1 = new Airport(44, 120, 100);
        Airport airport2 = new Airport(50, 112, 110);

        FlightReservation flightReservation1 = new FlightReservation("Alex", airport1, airport2);
        FlightReservation flightReservation2 = new FlightReservation("Alex", airport1, airport2);

        assertFalse(flightReservation1.equals(null),
                "FlightReservation: equals() returns the wrong value");
    }
    // check equality with HotelReservation

    @Test
    @Tag("score:1") @DisplayName("FlightReservation equals() Test5")
    void testEquals5() {
        Room[] rooms = {new Room("double"), new Room("double")};
        Hotel hotel1 = new Hotel("Hotel1", rooms);
        HotelReservation hotelReservation1 = new HotelReservation("Alex", hotel1, "double", 2);
        HotelReservation hotelReservation2 = new HotelReservation("Alex", hotel1, "double", 2);

        Airport airport1 = new Airport(44, 120, 100);
        Airport airport2 = new Airport(50, 112, 110);
        FlightReservation flightReservation1 = new FlightReservation("Alex", airport1, airport2);

        assertFalse(flightReservation1.equals(hotelReservation1), "HotelReservation: equals() returns the wrong value");
    }


}
class HotelReservationTest {    // 4 points

    @Test
    @Tag("score:1")
    @DisplayName("HotelReservation getNumOfNights() Test1")
    void getNumOfNights() {
        Room[] rooms = {new Room("double")};
        Hotel hotel1 = new Hotel("Hotel1", rooms);
        HotelReservation hotelReservation1 = new HotelReservation("Alex", hotel1, "double", 2);
        assertEquals(2, hotelReservation1.getNumOfNights(),
                "HotelReservation: getNumOfNights() returns the wrong number of nights.");
    }

    @Test
    @Tag("score:1")
    @DisplayName("HotelReservation getCost() Test1")
    void getCost() {
        Room[] rooms = {new Room("double")};
        Hotel hotel1 = new Hotel("Hotel1", rooms);
        HotelReservation hotelReservation1 = new HotelReservation("Alex", hotel1, "double", 2);
        assertEquals(18000, hotelReservation1.getCost(),
                "HotelReservation: getCost() returns the wrong cost.");
    }

    @Test
    @Tag("score:1")
    @DisplayName("HotelReservation equals() Test1")
    void testEquals1() {
        Room[] rooms = {new Room("double")};
        Hotel hotel1 = new Hotel("Hotel1", rooms);
        HotelReservation hotelReservation1 = new HotelReservation("Alex", hotel1, "double", 2);
        HotelReservation hotelReservation2 = hotelReservation1;

        assertTrue(hotelReservation1.equals(hotelReservation2),
                "HotelReservation: equals() returns the wrong value.");
    }
    @Test
    @Tag("score:1") @DisplayName("HotelReservation equals() Test2")
    void testEquals2() {
        Room[] rooms = {new Room("double"), new Room("king")};
        Hotel hotel1 = new Hotel("Hotel1", rooms);
        HotelReservation hotelReservation1 = new HotelReservation("Alex", hotel1, "double", 2);
        HotelReservation hotelReservation2 = new HotelReservation("Bob", hotel1, "king", 1);
        assertFalse(hotelReservation1.equals(hotelReservation2), "HotelReservation: equals() returns the wrong value");
    }


    @Test
    @Tag("score:1") @DisplayName("HotelReservation equals() Test3")
    void testEquals3() {
        Room[] rooms = {new Room("double"), new Room("double")};
        Hotel hotel1 = new Hotel("Hotel1", rooms);
        HotelReservation hotelReservation1 = new HotelReservation("Alex", hotel1, "double", 2);
        HotelReservation hotelReservation2 = new HotelReservation("Alex", hotel1, "double", 2);
        assertTrue(hotelReservation1.equals(hotelReservation2), "HotelReservation: equals() returns the wrong value");
    }

    // check equality with null
    @Test
    @Tag("score:1") @DisplayName("HotelReservation equals() Test4")
    void testEquals4() {
        Room[] rooms = {new Room("double"), new Room("double")};
        Hotel hotel1 = new Hotel("Hotel1", rooms);
        HotelReservation hotelReservation1 = new HotelReservation("Alex", hotel1, "double", 2);
        HotelReservation hotelReservation2 = new HotelReservation("Alex", hotel1, "double", 2);
        assertFalse(hotelReservation1.equals(null), "HotelReservation: equals() returns the wrong value");
    }
    // check equality with another reservation
    @Test
    @Tag("score:1") @DisplayName("HotelReservation equals() Test5")
    void testEquals5() {
        Room[] rooms = {new Room("double"), new Room("double")};
        Hotel hotel1 = new Hotel("Hotel1", rooms);
        HotelReservation hotelReservation1 = new HotelReservation("Alex", hotel1, "double", 2);
        HotelReservation hotelReservation2 = new HotelReservation("Alex", hotel1, "double", 2);

        Airport airport1 = new Airport(44, 120, 100);
        Airport airport2 = new Airport(50, 112, 110);
        FlightReservation flightReservation1 = new FlightReservation("Alex", airport1, airport2);

        assertFalse(hotelReservation1.equals(flightReservation1), "HotelReservation: equals() returns the wrong value");
    }
}

class BnBReservationTest {      // 2 points
    @Test
    @Tag("score:1") @DisplayName("BnBReservation reservationName() Test1")
    void reservationName() {
        Room[] rooms = {new Room("double")};
        Hotel hotel1 = new Hotel("Hotel1", rooms);

        BnBReservation bnBReservation = new BnBReservation("Alex", hotel1, "double", 2);
        assertEquals("Alex", bnBReservation.reservationName(),
                "BnBReservation: getCost() returns the wrong name.");
    }

    @Test
    @Tag("score:1") @DisplayName("BnBReservation getCost() Test1")
    void getCost() {
        Room[] rooms = {new Room("double")};
        Hotel hotel1 = new Hotel("Hotel1", rooms);

        BnBReservation bnBReservation = new BnBReservation("Alex", hotel1, "double", 2);
        assertEquals(20000, bnBReservation.getCost(),
                "BnBReservation: getCost() returns the wrong value.");
    }

    // check equality with HotelReservation
    @Test
    @Tag("score:1") @DisplayName("BnBReservation equals() Test1")
    void testEquals1() {
        Room[] rooms = {new Room("double"), new Room("double")};
        Hotel hotel1 = new Hotel("Hotel1", rooms);
        HotelReservation hotelReservation = new HotelReservation("Alex", hotel1, "double", 2);
        BnBReservation bnBReservation = new BnBReservation("Alex", hotel1, "double", 2);

        assertFalse(bnBReservation.equals(hotelReservation), "HotelReservation: equals() returns the wrong value");
    }
}

class BasketTest {      // 7 points

    @Test
    @Tag("score:1")
    @DisplayName("Basket add() Test2")
    void addTest2() {
        Basket basket = new Basket();
        int number = 0;
        for (int i = 0; i < 30; i++) {
            number = basket.add(new HotelReservation("Marty", new Hotel("Gatewater", new Room[]{new Room("queen")}), "queen", 4));
        }
        assertEquals(30, number, "Basket: add() returns the wrong number of reservations now in the basket when adding many items.");
    }

    @Test
    @Tag("score:2")
    @DisplayName("Basket getProducts() Test1")
    void getProducts2() {
        Basket basket = new Basket();
        HotelReservation hotelReservation1 = new HotelReservation("Alex", new Hotel("Hotel1", new Room[]{new Room("double")}), "double", 2);
        FlightReservation flightReservation1 = new FlightReservation("Bob", new Airport(44, 120, 100), new Airport(50, 112, 110));

        basket.add(hotelReservation1);
        basket.add(flightReservation1);

        Reservation[] expected = {hotelReservation1, flightReservation1};
        Reservation[] output = basket.getProducts();

        for (int i = 0; i < expected.length; i++) {
            assertEquals(expected[i], output[i],
                    "Basket: getProducts() returns the wrong order of reservations.");
        }
    }
    @Test
    @Tag("score:1") @DisplayName("Basket getNumOfReservations Test1")
    void getNumOfReservations() {
        Basket basket1 = new Basket();
        Reservation reservation1 = new BasketTest.FakeReservation("Alex");
        Reservation reservation2 = new BasketTest.FakeReservation("Bob");

        basket1.add(reservation1);
        basket1.add(reservation2);

        assertEquals(2, basket1.getNumOfReservations(),
                "Basket: getNumOfReservations() returns the wrong number of reservations");
    }


    @Test
    @Tag("score:1") @DisplayName("Basket remove() Test1")
    void remove() {
        Basket basket1 = new Basket();
        Airport airport1 = new Airport(44, 120, 100);
        Airport airport2 = new Airport(50, 112, 110);
        FlightReservation reservation1 = new FlightReservation("Alex", airport1, airport2);

        basket1.add(reservation1);

        boolean test = basket1.remove(reservation1);

        assertEquals(0, basket1.getNumOfReservations(),
                "Basket: remove() didn't remove the reservation");

        assertTrue(test, "Basket: remove() returns the wrong value");

    }
    private static class FakeReservation extends Reservation {

        public FakeReservation(String name) {
            super(name);
        }

        @Override
        public int getCost() {
            return 0;
        }

        @Override
        public boolean equals(Object o) {
            return false;
        }
    }
}
class CustomerTest {    // 7 points
    @Test
    @Tag("score:1")
    @DisplayName("Customer Constructor Test1")
    void customerConstructor_Test1() {
        Customer customer = new Customer("bob", 100);
        assertEquals("bob", customer.getName(),
                "Customer: getName() did not return the correct name");
        assertEquals(100, customer.getBalance(),
                "Customer: getBalance() did not return the correct balance");
        assertEquals(0, customer.getBasket().getNumOfReservations(),
                "Customer: getBasket() did not return the correct number of reservations");
    }

    @Test
    @Tag("score:1")
    @DisplayName("Customer addFunds() Test1")
    void addFunds_Test1() {
        Customer customer = new Customer("bob", 100);
        assertEquals(101, customer.addFunds(1),
                "Customer: addFunds() did not return the correct funds");
        assertEquals(101, customer.getBalance(),
                "Customer: getBalance() did not return the correct balance");
    }

    @Test
    @Tag("score:1")
    @DisplayName("Customer addToBasket(Reservation) Test1")
    void addToBasket_Test1_Reservation() {
        Customer customer = new Customer("bob", 100);
        Room[] rooms = {new Room("double")};
        Hotel hotel = new Hotel("barcelo", rooms);
        HotelReservation reservation = new HotelReservation("bob", hotel, "double", 2);

        assertEquals(1, customer.addToBasket(reservation),
                "Customer: addToBasket(Reservation) did not return the correct number of reservations in the basket");
    }
    @Test
    @Tag("score:1") @DisplayName("Customer addToBasket(HotelReservation) Test2")
    void addToBasket_Test2_Reservation() {
        Customer customer = new Customer("bob", 100);
        Room[] rooms = {new Room("double")};
        Hotel hotel = new Hotel("barcelo", rooms);

        assertEquals(1, customer.addToBasket(hotel, "double", 2, false),
                "Customer: addToBasket() for the Hotel type did not return the correct number of reservations in the basket");
    }

    @Test
    @Tag("score:1") @DisplayName("Customer addToBasket(Without Breakfast) Test3")
    void addToBasket_Test3_Reservation() {
        Customer customer = new Customer("Killua", 100);
        Room[] rooms = {new Room("double")};
        Hotel hotel = new Hotel("Greed Island", rooms);
        customer.addToBasket(hotel, "double", 2, false);

        int totalPrice = customer.getBasket().getTotalCost();

        assertEquals(18000, totalPrice,
                "Customer: addToBasket() for the Hotel type did not return the correct number of reservations in the basket");
    }
    @Test
    @Tag("score:1") @DisplayName("Customer addToBasket(With Breakfast) Test4")
    void addToBasket_Test4_Reservation() {
        Customer customer = new Customer("Killua", 100);
        Room[] rooms = {new Room("double")};
        Hotel hotel = new Hotel("Greed Island", rooms);
        customer.addToBasket(hotel, "double", 2, true);

        int totalPrice = customer.getBasket().getTotalCost();

        assertEquals(20000, totalPrice,
                "Customer: addToBasket() for the Hotel type did not return the correct number of reservations in the basket");
    }


    @Test
    @Tag("score:1") @DisplayName("Customer addToBasket(FlightReservation) Test5")
    void addToBasket_Test5_Reservation() {
        Customer customer = new Customer("bob", 100);
        Airport airport1 = new Airport(100, 200, 1000);
        Airport airport2 = new Airport(10, 20, 2000);

        assertEquals(1, customer.addToBasket(airport1, airport2),
                "Customer: addToBasket() for the Flight type did not return the correct number of reservations in the basket");
    }
    @Test
    @Tag("score:1") @DisplayName("Customer removeFromBasket(Reservation) Test1")
    void removeFromBasket_Test1() {
        Customer customer = new Customer("bob", 100);

        Room[] rooms = {new Room("double")};
        Hotel hotel = new Hotel("barcelo", rooms);
        Reservation reservation = new HotelReservation("bob", hotel, "double", 2);
        customer.addToBasket(reservation);

        assertTrue(customer.removeFromBasket(reservation),
                "Customer: removeFromBasket(Reservation) did not return the correct value");
    }


    @Test
    @Tag("score:1") @DisplayName("Customer removeFromBasket(Reservation) Test2")
    void removeFromBasket_Test2() {
        Customer customer = new Customer("Bob", 100);
        Customer customer2 = new Customer("Not Bob", 100);

        Room[] rooms = {new Room("double")};
        Hotel hotel = new Hotel("Average Hotel", rooms);
        Reservation reservation = new HotelReservation("Bob", hotel, "double", 2);
        customer.addToBasket(reservation);

        assertFalse(customer2.removeFromBasket(reservation),
                "Customer: removeFromBasket(Reservation) did not return the correct value");
    }
    // check if it works when we remove null
    @Test
    @Tag("score:1") @DisplayName("Customer removeFromBasket(Reservation) Test3")
    void removeFromBasket_Test3() {
        Customer customer = new Customer("Bob", 100);

        Room[] rooms = {};
        Hotel hotel = new Hotel("Average Hotel", rooms);

        Airport a1 = new Airport(44, 120, 100);
        Airport a2 = new Airport(45, 120, 100);

        assertFalse(customer.removeFromBasket(null),
                "Customer: removeFromBasket(Reservation) did not return the correct value");
    }

    @Test
    @Tag("score:1") @DisplayName("Customer checkOut() Test1")
    void checkout_Tes1() {
        Customer customer = new Customer("bob", 100000);

        Room[] rooms = {new Room("double")};
        Hotel hotel = new Hotel("barcelo", rooms);
        HotelReservation reservation = new HotelReservation("bob", hotel, "double", 2);
        customer.addToBasket(reservation);

        assertEquals(82000, customer.checkOut(),
                "Customer: checkOut() did not return the correct balance after checkOut");
    }


}