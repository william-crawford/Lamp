package cs2340.edu.gatech.lamp.model;

/**
 * Created by will on 2/25/18.
 */

public class ShelterAttribute {

    private final String name;
    private final String value;

    public ShelterAttribute(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }
}
