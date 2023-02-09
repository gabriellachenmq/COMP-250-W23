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


}

