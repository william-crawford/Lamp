package cs2340.edu.gatech.lamp.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by will on 2/17/18.
 */

public class Shelter {

    private String name;
    private Location location;
    private boolean hasSpace;
    private String phoneNumber;
    private List<ShelterAttribute> attributes;
    private String imageURL;
    public static ArrayList<Shelter> shelterList = new ArrayList<>();

    private static int nextId = 0;
    private int id;

    public Shelter(String name, Location location, boolean hasSpace, String phoneNumber, String imageURL) {
        this.name = name;
        this.location = location;
        this.hasSpace = hasSpace;
        this.imageURL = imageURL;
        this.phoneNumber = phoneNumber;
        this.id = nextId++;
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

    public List<ShelterAttribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<ShelterAttribute> attributes) {
        this.attributes = attributes;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getId() {
        return id;
    }

    public static Shelter getShelter(int shelterID) {
        return shelterList.get(shelterID);
    }

    @Override
    public boolean equals(Object other) {
        return !(other == null || !(other instanceof Shelter)) && this.id == ((Shelter) other).getId();
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {
        return name;
    }

    //M6-------------------------------------------------
    private String[] info;
    private String shelterName;
    private String capacity;
    private String gender;
    private String longitude;
    private String latitude;
    private String address;

    public Shelter(String[] info) {
        name = info[1];
        this.info = info;
        shelterName = info[1];
        capacity = info[2];
        gender = info[3];
        longitude = info[4];
        latitude = info[5];
        address = info[6];
        phoneNumber = info[8];
    }

    public String getDetails() {
        String[] detail = getInfo();
        String details = "Unique Key: " + detail[0] + "\nShelter Name: " + detail[1] + "\nCapacity: " + detail[2] + "\nRestrictions: " + detail[3] + "\nLongitude: " + detail[4]
                + "\nLatitude: " + detail[5] + "\nAddress: " + detail[6] + "\nSpecial Notes: " + detail[7] + "\nPhone Number: " + detail[8];
        return details;
    }

    public String[] getInfo() {
        return info;
    }
    //M6-------------------------------------------------
}
