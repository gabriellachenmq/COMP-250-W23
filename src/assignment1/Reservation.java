package assignment1;

public abstract class Reservation {
    private String clientName;

    public Reservation(String name){
        this.clientName = name;
    }

    public final String reservationName(){
        return this.clientName;
    }

    public abstract int getCost();
    public abstract boolean equals(Object obj);

}


