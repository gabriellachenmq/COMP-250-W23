package assignment1;



public class Room {
    private String roomType;
    private int roomPrice;
    private boolean availability;

    public Room(String r) {

        if (!r.equals("king") && !r.equals("queen") && !r.equals("double")) {
            throw new IllegalArgumentException("No room of such type can be created");
        }
        this.availability = true;
        this.roomType = r;
        if (roomType.equals("double"))
            roomPrice = 9000;
        if (roomType.equals("queen"))
            roomPrice = 11000;
        if (roomType.equals("king"))
            roomPrice = 15000;

    }
//Copy constructor
    public Room(Room room){
        this.roomType = room.roomType;
        this.roomPrice = room.roomPrice;
        this.availability = room.availability;

    }

    public String getType(){
        return this.roomType;
    }

    public int getPrice(){
        return this.roomPrice;
    }

    public boolean changeAvailability(){
        this.availability = !this.availability;
        return this.availability;
    }

    public static Room findAvailableRoom(Room[] roomList, String typeNeed){

        Room availableRoom = null;

        for (int i = 0; i<roomList.length; i++){
            if (roomList[i] == null)
                continue;
            else if (roomList[i].roomType.equals(typeNeed)) {
                availableRoom = roomList[i];
                break;
            }
        }
        return availableRoom;
    }

    public static boolean makeRoomAvailable(Room[] roomAry, String roomChanged){

        for (int i=0; i<roomAry.length; i++){
            if (roomAry[i] == null)
                continue;
            else if (roomAry[i].roomType.equals(roomChanged) && !roomAry[i].availability) {
                roomAry[i].availability = true;
                return true;
            }

        }return false;

    }



    public static void main(String[] args){
/*
        Room room = new Room("double");
        Room copyRoom = new Room(room);
        System.out.println(room == copyRoom);*/


        /*Room[] rooms = {new Room("king"), null, new Room("queen"), new Room("double"),new Room("queen")};
        System.out.println(rooms[2].equals(Room.findAvailableRoom(rooms, "queen")));*/






    }


}

