package assignment1;

public class Room {
    private String room_type;
    private int room_price;
    private boolean availability;

    public Room(String r) {

        if (r != "double" && r != "queen" && r != "king") {
            throw new IllegalArgumentException("No room of such type can be created");
        }
        this.availability = true;
        this.room_type = r;
        if (room_type == "double")
            room_price = 9000;
        if (room_type == "queen")
            room_price = 11000;
        if (room_type == "king")
            room_price = 15000;

    }
//Copy constructor
    public Room(Room room){
        this.room_type = room.room_type;;
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
            if (room_list[i].room_type == type_need){
                avaliable_room = room_list[i];
                break;

        }
        return avaliable_room;
    }

    public static boolean makeRoomAvailable(Room[] room_ary, String type_changed){
        for (int i=0; i<room_ary.length; i++){
            if (!room_ary[i].availability)
                room_ary[i].availability = !room_ary[i].availability;


        }
        return true;

    }


    public static void main(String[] args){
        Room[] rooms = {new Room("king"), new Room("queen"), new Room("double"),new Room("queen")};
        //System.out.println(findAvailableRoom(rooms, "queen"));
        System.out.println(rooms[1].equals(findAvailableRoom(rooms, "queen")));




    }


}

