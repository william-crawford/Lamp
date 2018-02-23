package cs2340.edu.gatech.lamp.model;

/**
 * Created by will on 2/17/18.
 */

public class Shelter {

    private String name;
    private Location location;
    private boolean hasSpace;

    public Shelter(String name, Location location, boolean hasSpace) {
        this.name = name;
        this.location = location;
        this.hasSpace = hasSpace;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public boolean isHasSpace() {
        return hasSpace;
    }

    public void setHasSpace(boolean hasSpace) {
        this.hasSpace = hasSpace;
    }
}
