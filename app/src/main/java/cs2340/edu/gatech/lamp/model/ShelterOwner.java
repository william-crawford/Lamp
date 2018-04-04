package cs2340.edu.gatech.lamp.model;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

/**
 * Created by Tucker on 3/4/2018.
 */

public class ShelterOwner extends ShelterUser {

    public ShelterOwner(FirebaseUser user) {
        super(user);
    }

    public void writeNewUser() {
        dbRef.child("users").child(userID).setValue(name);
        dbRef.child("users").child(userID).setValue(email);
        dbRef.child("users").child(userID).child("type").setValue("ShelterOwner");
        dbRef.child("ShelterOwner").child(userID).setValue(true);
    }
}
