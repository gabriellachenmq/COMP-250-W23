package assignment1;

public class Hotel {

    private String hotelName;
    private Room[] hotelRooms;

    public Hotel(String name, Room[] rooms) {
        this.hotelName = name;
        Room[] roomsCopy = new Room[rooms.length];
        for (int i = 0; i < rooms.length; i++) {
            roomsCopy[i] = new Room(rooms[i]);
        }
        this.hotelRooms = roomsCopy;
    }

    public int reserveRoom(String roomReserved) {

        if (roomReserved == null || Room.findAvailableRoom(this.hotelRooms, roomReserved) == null)
            throw new IllegalArgumentException("No room of such type can be created");

        Room newRoom = Room.findAvailableRoom(this.hotelRooms, roomReserved);
        newRoom.changeAvailability();
        return newRoom.getPrice();

    }

    public boolean cancelRoom(String roomCanceled){

        if (roomCanceled == null)
            return false;
        Room oldRoom = new Room(roomCanceled);
        return oldRoom.makeRoomAvailable(this.hotelRooms, roomCanceled);

    }
    public static void main(String[] args){
        Room[] rooms = {new Room("double")};
        Hotel hotel1 = new Hotel("Hotel1", rooms);
        System.out.println(hotel1.reserveRoom("double"));
        System.out.println(hotel1.cancelRoom("double"));


    }



}




