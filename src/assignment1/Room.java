package assignment1;



public class Room {
    private String room_type;
    private int room_price;
    private boolean availability;

    public Room(String r) {

        if (!r.equals("king") && !r.equals("queen") && !r.equals("double")) {
            throw new IllegalArgumentException("No room of such type can be created");
        }
        this.availability = true;
        this.room_type = r;
        if (room_type.equals("double"))
            room_price = 9000;
        if (room_type.equals("queen"))
            room_price = 11000;
        if (room_type.equals("king"))
            room_price = 15000;

    }
//Copy constructor
    public Room(Room room){
        this.room_type = room.room_type;
        this.room_price = room.room_price;
        this.availability = room.availability;

    }

    public String getType(){
        return this.room_type;
    }

    public int getPrice(){
        return this.room_price;
    }

    public boolean changeAvailability(){
        this.availability = !this.availability;
        return this.availability;
    }

    public static Room findAvailableRoom(Room[] room_list, String type_need){

        Room avaliable_room = null;

        for (int i = 0; i<room_list.length; i++){
            if (room_list[i] == null)
                continue;
            else if (room_list[i].room_type.equals(type_need)) {
                avaliable_room = room_list[i];
                break;
            }
        }
        return avaliable_room;
    }

    public static boolean makeRoomAvailable(Room[] room_ary, String room_changed){

        for (int i=0; i<room_ary.length; i++){
            if (room_ary[i] == null)
                continue;
            else if (room_ary[i].room_type.equals(room_changed) && !room_ary[i].availability) {
                room_ary[i].availability = true;
                return true;
            }
        }
        return false;
    }



    public static void main(String[] args){

        Room room = new Room("double");
        Room copyRoom = new Room(room);
        System.out.println(room == copyRoom);


        /*Room[] rooms = {new Room("king"), null, new Room("queen"), new Room("double"),new Room("queen")};
        System.out.println(rooms[2].equals(Room.findAvailableRoom(rooms, "queen")));*/

        /*Room room1 = new Room("double");
        Room room2 = new Room("double");
        Room[] rooms = {room1,room2} ;// Should go through room null without return false
        System.out.println(makeRoomAvailable(rooms, "double"));*/




    }


}

