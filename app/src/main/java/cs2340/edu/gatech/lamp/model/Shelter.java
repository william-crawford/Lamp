package cs2340.edu.gatech.lamp.model;
import com.google.android.gms.maps.model.LatLng;

/**
 * Created by will on 2/17/18.
 */

public class Shelter {

    private String name;
    private Address address;
    private boolean hasSpace;

    public Shelter(String name, Address address, boolean hasSpace) {
        this.name = name;
        this.address = address;
        this.hasSpace = hasSpace;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public boolean isHasSpace() {
        return hasSpace;
    }

    public void setHasSpace(boolean hasSpace) {
        this.hasSpace = hasSpace;
    }
}
