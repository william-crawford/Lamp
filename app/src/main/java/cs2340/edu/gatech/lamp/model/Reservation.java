package cs2340.edu.gatech.lamp.model;

/**
 * Created by will on 3/27/18.
 */

public class Reservation {

    private String userID;
    private int spacesReserved;

    public Reservation(String userID) {
        this.userID = userID;
        this.spacesReserved = 1;
    }

    public String getUserID() {
        return userID;
    }

    public int getSpacesReserved() {
        return spacesReserved;
    }

    public boolean decrement() {
        if (spacesReserved == 1) {
            return false;
        }
        spacesReserved--;
        return true;
    }

    public void increment() {
        spacesReserved++;
    }
}
