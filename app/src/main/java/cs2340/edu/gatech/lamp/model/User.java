package cs2340.edu.gatech.lamp.model;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

@SuppressWarnings("ALL")
public abstract class User {
    final String userID;
    final String email;
    final String name;
    final DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference();

    User(FirebaseUser user) {
        userID = user.getUid();
        email = user.getEmail();
        name = user.getDisplayName();
    }

    /**
     * Getter for userID
     * @return returns a user's ID
     */
    public String getUserID() {
        return userID;
    }

    /**
     * Abstract method to write a User to dbRef
     */
    public abstract void writeNewUser();
}
