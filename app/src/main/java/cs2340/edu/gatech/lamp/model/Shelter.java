package cs2340.edu.gatech.lamp.model;

import java.util.List;

/**
 * Created by will on 2/17/18.
 */

public class Shelter {

    private String name;
    private Location location;
    private boolean full;
    private String phoneNumber;
    private List<ShelterAttribute> attributes;
    private String imageURL;

    private static int nextId = 0;
    private int id;

    public Shelter(String name, Location location, boolean full, String phoneNumber, String imageURL) {
        this.name = name;
        this.location = location;
        this.full = full;
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

    public boolean isFull() {
        return full;
    }

    public void setFull(boolean full) {
        this.full = full;
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

    @Override
    public boolean equals(Object other) {
        return !(other == null || !(other instanceof Shelter)) && this.id == ((Shelter) other).getId();
    }

    @Override
    public int hashCode() {
        return id;
    }
}
