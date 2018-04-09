package cs2340.edu.gatech.lamp.model;

import com.google.android.gms.maps.model.LatLng;
import com.google.firebase.database.Exclude;

/**
 * Created by will on 2/17/18.
 */

public class Location {

    private double latitude;
    private double longitude;

    //private String street;
    //private String city;
    //private String state;
    //private String zip;

    private String address;

    /*
    public Location(double latitude, double longitude, String street, String city, String state, String zip) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.street = street;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.latLng = new LatLng(latitude, longitude);
    }
    */

    public Location(double latitude, double longitude, String address) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.address = address;
    }

    /*
    public Location(double latitude, double longitude, String street) {
        this(latitude, longitude, street, "Atlanta", "GA", "30332");
    }
    */

    @Exclude
    public LatLng getLatLng() {
        return  new LatLng(latitude, longitude);
    }

    @Exclude
    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    @Exclude
    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    @Exclude
    public String getAddress() {
        return this.address;
    }

    public void setAddress() {
        this.address = address;
    }

    /*
    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }
    */

    public double distanceTo(LatLng other) {
        float[] result = new float[1];
        android.location.Location.distanceBetween(
                latitude,
                longitude,
                other.latitude,
                other.longitude,
                result
        );
        return result[0] / 1609;
    }


}
