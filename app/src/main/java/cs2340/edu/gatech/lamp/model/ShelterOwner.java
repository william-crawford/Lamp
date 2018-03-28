package cs2340.edu.gatech.lamp.model;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tucker on 3/4/2018.
 */

public class ShelterOwner extends ShelterUser {

    private List<String> shelterIDs = new ArrayList<>();
    public ShelterOwner(FirebaseUser user) {
        super(user);
    }

    public void addShelterID(String key) {
        shelterIDs.add(key);
    }

    public void writeNewUser() {
        dbRef.child("users").child(userID).setValue(name);
        dbRef.child("users").child(userID).setValue(email);
        dbRef.child("users").child(userID).child("type").setValue("ShelterOwner");
        dbRef.child("ShelterOwner").child(userID).setValue(true);
    }
}
