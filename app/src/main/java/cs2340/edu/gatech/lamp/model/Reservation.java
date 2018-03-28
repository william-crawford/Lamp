package cs2340.edu.gatech.lamp.model;

/**
 * Created by will on 3/27/18.
 */

public class Reservation {

    private String userID;
    private int spacesReserved;

    @Override
    public int hashCode() {
        return userID.hashCode();
    }

    @Override
    public boolean equals(Object other) {
        return (other != null && other instanceof Reservation && userID.equals(((Reservation) other).userID));
    }

}
