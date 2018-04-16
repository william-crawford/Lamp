package cs2340.edu.gatech.lamp.model;

import com.google.firebase.auth.FirebaseUser;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tucker on 3/4/2018.
 */

@SuppressWarnings("MismatchedQueryAndUpdateOfCollection")
public class ShelterOwner extends ShelterUser {

    private final List<String> shelterIDs = new ArrayList<>();

    /**
     * Creates a ShelterOwner
     * @param user a FirebaseUser that provides attributes for a ShelterOwner
     */
    public ShelterOwner(FirebaseUser user) {
        super(user);
    }

    /**
     * Adds a shelter's key to the ShelterOwners shelterIDs
     * @param key the key to be added
     */
    public void addShelterID(String key) {
        shelterIDs.add(key);
    }

    /**
     * Writes a new ShelterOwner to dbRef
     */
    public void writeNewUser() {
        dbRef.child("users").child(userID).setValue(name);
        dbRef.child("users").child(userID).setValue(email);
        dbRef.child("users").child(userID).child("type").setValue("ShelterOwner");
        dbRef.child("ShelterOwner").child(userID).setValue(true);
    }
}
