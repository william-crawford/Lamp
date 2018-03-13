package cs2340.edu.gatech.lamp.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by will on 2/17/18.
 */

public class Shelter {

    private String name;
    private Location location;
    private boolean hasSpace = true;
    private String phoneNumber;
    private String imageURL;

    private String capacity;
    private String notes;
    private String restrictions;

    private String key;

    public Shelter(String name, Location location, boolean hasSpace, String phoneNumber, String imageURL, String uniqueKey) {
        this.name = name;
        this.location = location;
        this.hasSpace = hasSpace;
        this.imageURL = imageURL;
        this.phoneNumber = phoneNumber;
        this.key = uniqueKey;
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

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getRestrictions() {
        return restrictions;
    }

    public void setRestrictions(String restrictions) {
        this.restrictions = restrictions;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getCapacity() {
        return capacity;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    public String getKey() {
        return key;
    }

    @Override
    public boolean equals(Object other) {
        return !(other == null || !(other instanceof Shelter)) && this.key.equals(((Shelter) other).key);
    }

    @Override
    public int hashCode() {
        return key.hashCode();
    }

    @Override
    public String toString() {
        return name;
    }

    //M6------------------------------------------------

    public Shelter(String[] info) {
        name = info[1];
        capacity = info[2];
        restrictions = info[3];
        double longitude = Double.parseDouble(info[4]);
        double latitude = Double.parseDouble(info[5]);
        String address = info[6];
        location = new Location(latitude, longitude, address);
        notes = info[7];
        phoneNumber = info[8];
    }

    public String getDetails() {
        String[] detail = getInfo();
        String details = "Unique Key: " + detail[0] + "\nShelter Name: " + detail[1] + "\nCapacity: " + detail[2] + "\nRestrictions: " + detail[3] + "\nLongitude: " + detail[4]
                + "\nLatitude: " + detail[5] + "\nAddress: " + detail[6] + "\nSpecial Notes: " + detail[7] + "\nPhone Number: " + detail[8];
        return details;
    }

    public String[] getInfo() {
        String[] info = new String[9];
        info[0] = key;
        info[1] = name;
        info[2] = capacity;
        info[3] = restrictions;
        info[4] = Double.toString(location.getLongitude());
        info[5] = Double.toString(location.getLatitude());
        info[6] = location.getAddress();
        info[7] = notes;
        info[8] = phoneNumber;

        return info;
    }
    //M6-------------------------------------------------
}
