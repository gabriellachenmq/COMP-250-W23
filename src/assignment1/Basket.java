package assignment1;

public class Basket {
    private Reservation[] reserveList;

    public Basket() {
        this.reserveList = new Reservation[0];
    }

    public Reservation[] getProducts() {
        return reserveList.clone();
    }

    public int add(Reservation newRes) {

        Reservation[] newReservations = new Reservation[reserveList.length + 1];
        System.arraycopy(reserveList, 0, newReservations, 0, reserveList.length);//copying entire old list
        newReservations[newReservations.length - 1] = newRes;//add new object into the end of the list
        reserveList = newReservations;
        return reserveList.length;
    }

    public boolean remove(Reservation oldRes) {
        int index = 0;
        int indicator = 0;

        for (int i = 0; i < reserveList.length; i++) {
            if (reserveList[i].equals(oldRes)) {
                index = i;
                indicator = 1;
                break;
            }
        }
        if (indicator ==0)
            return false;

        Reservation[] newReservations = new Reservation[reserveList.length - 1];
        System.arraycopy(reserveList, 0, newReservations, 0, index);
        int numLeft = reserveList.length - index - 1;
        System.arraycopy(reserveList, index + 1, newReservations, index, numLeft);
        reserveList = newReservations;
        return true;
    }

    public void clear() {
        reserveList = new Reservation[0];
    }

    public int getNumOfReservations() {
        return reserveList.length;
    }

    public int getTotalCost() {
        int totalCost = 0;
        for (int i=0; i<reserveList.length; i++) {
            totalCost += reserveList[i].getCost();
        }
        return totalCost;
    }
}
