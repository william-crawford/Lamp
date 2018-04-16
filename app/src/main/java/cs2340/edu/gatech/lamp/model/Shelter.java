package cs2340.edu.gatech.lamp.model;

//import android.content.Intent;
//import android.util.Log;

//import com.google.firebase.database.DataSnapshot;
//import com.google.firebase.database.DatabaseError;
//import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Exclude;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
//import java.util.Iterator;
import java.util.List;

//import static android.content.ContentValues.TAG;
import static cs2340.edu.gatech.lamp.utils.ShelterManager.updateShelter;

/**
 * Created by will on 2/17/18.
 */

@SuppressWarnings("RedundantIfStatement")
public class Shelter {

    private String name;
    private Location location;
    private String phoneNumber;
    private String imageURL;

    private String capacity;
    private String notes;
    private String restrictions;

    private int spacesFilled;
    private int numericCapacity;

    private List<Reservation> reservations = new ArrayList<>();

    /**
     * Enum representing the GenderPolicy of a Shelter
     */
    public enum GenderPolicy {
        NO_FILTER(""),
        MALE_ONLY("Male Only"),
        FEMALE_ONLY("Female Only"),
        ANYONE("Anyone");

        private String label;

        GenderPolicy(String value){
            this.label = value;
        }

        /**
         * ToString method for a GenderPolicy
         * @return a String representation of a GenderPolicy
         */
        public String toString(){
            return label;
        }
    }

    /**
     * Enum representing the AgePolicy of a Shelter
     */
    public enum AgePolicy {
        NO_FILTER(""),
        FAMILIES_WITH_NEWBORNS("Families with Newborns"),
        CHILDREN("Children"),
        YOUNG_ADULTS("Young Adults"),
        ANYONE("Anyone");

        private String label;

        AgePolicy(String value){
            this.label = value;
        }

        /**
         * ToString method for a AgePolicy
         * @return a String representation of a AgePolicy
         */
        public String toString(){
            return label;
        }
    }

    private GenderPolicy genderPolicy;
    private AgePolicy agePolicy;

    private String key;

    /**
     * Getter for name
     * @return returns name
     */
    @Exclude
    public String getName() {
        return name;
    }

    /**
     * Setter for name
     * @param name a name to be set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter for location
     * @return returns location
     */
    @Exclude
    public Location getLocation() {
        return location;
    }

    /**
     * Setter for location
     * @param location a location to be set
     */
    public void setLocation(Location location) {
        this.location = location;
        updateShelter(this);
    }

    /**
     * Method that determines if a Shelter is full
     * @return returns weather a Shelter is full
     */
    public boolean isFull() {
        return numericCapacity - spacesFilled <= 0;
    }

    /**
     * Getter for imageURL
     * @return returns imageuRL
     */
    @Exclude
    public String getImageURL() {
        return imageURL;
    }

    /**
     * Setter for imageURL
     * @param imageURL imageURL to be set
     */
    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
        updateShelter(this);
    }

    /**
     * Getter for phoneNumber
     * @return returns phoneNumber
     */
    @Exclude
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Setter for phoneNumber
     * @param phoneNumber a phoneNumber to be set
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        updateShelter(this);
    }

    /**
     * Getter for restrictions
     * @return returns restrictions
     */
    @Exclude
    public String getRestrictions() {
        return restrictions;
    }

    /**
     * Setter for restrictions
     * @param restrictions restrictions to be set
     */
    public void setRestrictions(String restrictions) {
        this.restrictions = restrictions;
        updateShelter(this);
    }

    /**
     * Getter for notes
     * @return returns notes
     */
    @Exclude
    public String getNotes() {
        return notes;
    }

    /**
     * Setter for Notes
     * @param notes notes to be set
     */
    public void setNotes(String notes) {
        this.notes = notes;
        updateShelter(this);
    }

    /**
     * Getter for capacity
     * @return returns capacity
     */
    @Exclude
    public String getCapacity() {
        return capacity;
    }

    /**
     * Setter for capacity
     * @param capacity a capacity to be set
     */
    public void setCapacity(String capacity) {
        this.capacity = capacity;
        updateShelter(this);
    }

    /**
     * Getter for numericCapacity
     * @return returns numericCapacity
     */
    @Exclude
    public int getNumericCapacity() {
        return numericCapacity;
    }

    /**
     * Getter for spacesFilled
     * @return returns spacesFilled
     */
    @Exclude
    public int getSpacesFilled() {
        return spacesFilled;
    }

    /**
     * Getter for spaces vacant
     * @return returns
     */
    @Exclude
    public int getSpacesVacant() {
        return numericCapacity - spacesFilled;
    }

    /**
     * Setter for spacesFilled
     * @param spacesFilled spacesFilled to be set
     */
    public void setSpacesFilled(int spacesFilled) {
        this.spacesFilled = spacesFilled;
    }

    /**
     * Getter for key
     * @return returns key
     */
    @Exclude
    public String getKey() {
        return key;
    }

    @Override
    public boolean equals(Object other) {
        if(other == null || !(other instanceof Shelter)) {
            return false;
        } else if (this.key.equals(((Shelter) other).key)) {
            return true;
        } else {
            return false;
        }
    }

    @Exclude
    @Override
    public int hashCode() {
        return key.hashCode();
    }

    @Exclude
    @Override
    public String toString() {
        return name;
    }

    /**
     * Creates a Shelter with default attributes
     */
    public Shelter() {
        //This exists
    }

    /**
     * Creates a Shelter
     * @param info information to set attributes of a Shelter
     */
    public Shelter(String[] info) {
        key = info[0];
        name = info[1];
        capacity = info[2];
        restrictions = info[3];
        double longitude = Double.parseDouble(info[4]);
        double latitude = Double.parseDouble(info[5]);
        String address = info[6];
        location = new Location(latitude, longitude, address);
        notes = info[7];
        phoneNumber = info[8];
        imageURL = info[9];
        spacesFilled = Integer.parseInt(info[10]);

        // DON'T READ THIS IF YOU LIKE SLEEPING COMFORTABLY AT NIGHT
        if (restrictions.contains("Women")) {
            genderPolicy = GenderPolicy.FEMALE_ONLY;
        } else if (restrictions.contains("Men")) {
            genderPolicy = GenderPolicy.MALE_ONLY;
        } else {
            genderPolicy = GenderPolicy.ANYONE;
        }

        if (restrictions.contains("Children")) {
            agePolicy = AgePolicy.CHILDREN;
        } else if (restrictions.contains("newborns")) {
            agePolicy = AgePolicy.FAMILIES_WITH_NEWBORNS;
        } else if (restrictions.contains("Young")) {
            agePolicy = AgePolicy.YOUNG_ADULTS;
        } else {
            agePolicy = AgePolicy.ANYONE;
        }

        StringBuilder sb = new StringBuilder();
        for (char c : capacity.toCharArray()) {
            if (c >= 48 && c < 58) {
                sb.append(c);
            }
        }
        try {
            numericCapacity = Integer.parseInt(new String(sb));
        } catch (Exception e) {
            numericCapacity = 0;
        }
    }

    /**
     * Gives a Reservation by userID
     * @param userID the ID of a User
     * @return returns a reservation
     */
    public Reservation getReservation(String userID) {
        for (Reservation r : reservations) {
            if (r.getUserID().equals(Model.getInstance().getCurrentUser().getUserID())) {
                return r;
            }
        }
        return null;
    }

    /**
     * Decrements a Shelter's spacesFilled
     * @param user a HomelessUser
     * @return returns weather the number of filled spaces was decremented
     */
    public boolean decreaseReservation(HomelessUser user) {
        /*
        Iterator<Reservation> i = reservations.iterator();
        while (i.hasNext()) {
            Reservation r = i.next();
            if (r.getUserID().equals(Model.getInstance().getCurrentUser().getUserID())) {
                if (!r.decrement()) {
                    i.remove();
                }
                spacesFilled--;
                return true;
            }
        }
        return false;
        */
        if (spacesFilled == 0) {
            return false;
        }
        spacesFilled--;
        return true;
    }

    /**
     * Increments a Shelter's spacesFilled regardless
     * @param user a HomelessUser
     * @return returns weather the number of filled spaces was incremented
     */
    public boolean forceIncreaseReservation(HomelessUser user) {
        if (isFull()) {
            return false;
        } else {
            spacesFilled++;
            return true;
        }
    }

    /**
     * Increments a Shelter's spacesFilled
     * @param user a HomelessUser
     * @return returns weather the number of filled spaces was incremented
     */
    public boolean increaseReservation(HomelessUser user) {
        if (isFull()) {
            return false;
        } else {
            spacesFilled++;
            for (Reservation r : reservations) {
                if (r.getUserID().equals(Model.getInstance().getCurrentUser().getUserID())) {
                    r.increment();
                    return true;
                }
            }
            reservations.add(new Reservation(Model.getInstance().getCurrentUser().getUserID()));
            return true;
        }
    }

    /**
     * Getter for info
     * @return returns info
     */
    @Exclude
    public String[] getInfo() {
        String[] info = new String[11];
        info[0] = key;
        info[1] = name;
        info[2] = capacity;
        info[3] = restrictions;
        info[4] = Double.toString(location.getLongitude());
        info[5] = Double.toString(location.getLatitude());
        info[6] = location.getAddress();
        info[7] = notes;
        info[8] = phoneNumber;
        info[9] = imageURL;
        info[10] = String.valueOf(spacesFilled);

        return info;
    }

    /**
     * Checks a Shelter's GenderPolicy
     * @param gp a GenderPolicy
     * @return returns weather a Shelter's GenderPolicy is implemented
     */
    public boolean checkGPFilter(GenderPolicy gp) {
        return gp == null || gp == GenderPolicy.NO_FILTER || gp == genderPolicy;
    }

    /**
     * Checks a Shelter's AgePolicy
     * @param ap a AgePolicy
     * @return returns weather a Shelter's AgePolicy is implemented
     */
    public boolean checkAPFilter(AgePolicy ap) {
        return ap == null || ap == AgePolicy.NO_FILTER || ap == agePolicy;
    }

    /**
     * Checks a a Shelter's name
     * @param filter a name filter
     * @return returns weather a Shelter's name is acceptable by the filter
     */
    public boolean checkNameFilter(String filter) {
        return filter.length() == 0 || name.toLowerCase().contains(filter.toLowerCase());
    }
}
