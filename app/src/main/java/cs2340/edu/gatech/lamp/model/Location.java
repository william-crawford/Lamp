package cs2340.edu.gatech.lamp.model;

import com.google.android.gms.maps.model.LatLng;
import com.google.firebase.database.Exclude;


@SuppressWarnings("ALL")
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

    /**
     * Creates a Location
     *
     * @param latitude  the latitude of a location
     * @param longitude the longitude of a location
     * @param address the address of a location
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

    /**
     * Getter for latLng
     * @return returns latLng
     */
    @Exclude
    public LatLng getLatLng() {
        return  new LatLng(latitude, longitude);
    }

    /**
     * Getter for latitude
     * @return returns latitude
     */
    @Exclude
    public double getLatitude() {
        return latitude;
    }

    /**
     * Setter for latitude
     * @param latitude latitude to be set
     */
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    /**
     * Getter for longitude
     * @return returns longitude
     */
    @Exclude
    public double getLongitude() {
        return longitude;
    }

    /**
     * Setter for longitude
     * @param longitude longitude to be set
     */
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    /**
     * Getter for address
     * @return returns address
     */
    @Exclude
    public String getAddress() {
        return this.address;
    }

    /**
     * Setter for address
     * @param address address to be set
     */
    public void setAddress(String address) {
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

    /**
     * Calculates the distance from users location to the destination
     * @param other the LatLng of the destination
     * @return returns the distance
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
