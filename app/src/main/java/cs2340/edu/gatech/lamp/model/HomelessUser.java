package cs2340.edu.gatech.lamp.model;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

import java.util.List;

/**
 * Created by Tucker on 3/4/2018.
 */

public class HomelessUser extends User {

    public HomelessUser(FirebaseUser user) {
        super(user);
    }

    private String reservedShelterID;

    public void writeNewUser() {
        dbRef.child("users").child(userID).setValue(name);
        dbRef.child("users").child(userID).setValue(email);
        dbRef.child("users").child(userID).child("type").setValue("HomelessUser");
        dbRef.child("HomelessUsers").child(userID).setValue(true);
    }
}
