package cs2340.edu.gatech.lamp.model;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

/**
 * Created by Tucker on 3/4/2018.
 */

public class Admin extends ShelterUser {
    /**
     * Creates a new Admin
     *
     * @param user a FirebaseUser that provides attributes for an Admin
     */
    public Admin(FirebaseUser user) {
        super(user);
    }

    /**
     * Writes a new Admin to dbRef
     */
    public void writeNewUser() {
        dbRef.child("users").child(userID).setValue(name);
        dbRef.child("users").child(userID).setValue(email);
        dbRef.child("users").child(userID).child("type").setValue("Admin");
        dbRef.child("Admins").child(userID).setValue(true);

    }
}
