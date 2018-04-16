package cs2340.edu.gatech.lamp.model;

/**
 * Created by will on 3/27/18.
 */

public class Reservation {

    private final String userID;
    private int spacesReserved;

    /**
     * Creates a Reservation
     * @param userID ID of a User
     */
    public Reservation(String userID) {
        this.userID = userID;
        this.spacesReserved = 1;
    }

    /**
     * Getter for userID
     * @return returns a users ID
     */
    public String getUserID() {
        return userID;
    }

    /**
     * Getter for spacesReserved
     * @return returns the number of reserved spaces
     */
    public int getSpacesReserved() {
        return spacesReserved;
    }

    /**
     * Decrements spacesReserved
     * @return returns weather the number of reserved spaces was decremented
     */
    public boolean decrement() {
        if (spacesReserved == 1) {
            return false;
        }
        spacesReserved--;
        return true;
    }

    /**
     * Increments spacesReserved
     */
    public void increment() {
        spacesReserved++;
    }
}
