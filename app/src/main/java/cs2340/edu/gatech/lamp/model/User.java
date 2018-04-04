package cs2340.edu.gatech.lamp.model;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Tucker on 3/4/2018.
 */

public abstract class User {
    protected String userID;
    protected String email;
    protected String name;
    protected DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference();

    protected User(FirebaseUser user) {
        userID = user.getUid();
        email = user.getEmail();
        name = user.getDisplayName();
    }

    public abstract void writeNewUser();
}
