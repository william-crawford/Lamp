package cs2340.edu.gatech.lamp.utils;

import android.content.Context;
import android.util.Log;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import cs2340.edu.gatech.lamp.model.Admin;
import cs2340.edu.gatech.lamp.model.HomelessUser;
import cs2340.edu.gatech.lamp.model.Model;
import cs2340.edu.gatech.lamp.model.ShelterOwner;

public class DatabaseReadCalls {
    /**
     * Returns user type to app
     * @param user FirebaseUser that the database reference will be created from
     */
    public static void getUserType(final FirebaseUser user, final Context context) {
        DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference("users/")
                .child(user.getUid()).child("type");
        dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (!dataSnapshot.exists()) {
                    HelperUI.signOut(context);
                    HelperUI.goToWelcome(context);
                } else if (dataSnapshot.getValue().equals("Admin")) {
                    Model.getInstance().setCurrentUser(new Admin(user));
                    Model.load(context);
                    HelperUI.goToDefault(context);
                } else if (dataSnapshot.getValue().equals("HomelessUser")) {
                    Model.getInstance().setCurrentUser(new HomelessUser(user));
                    Model.load(context);
                    HelperUI.goToDefault(context);
                } else if (dataSnapshot.getValue().equals("ShelterOwner")) {
                    Model.getInstance().setCurrentUser(new ShelterOwner(user));
                    Model.load(context);
                    HelperUI.goToDefault(context);
                } else {
                    Log.d("getUserType", "Type not recognized");
                    HelperUI.goToWelcome(context);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // If type retrieval fails, log error.
                Log.w("getUserType", "Database Reading Error", databaseError.toException());
                HelperUI.goToWelcome(context);
            }
        });
    }
}
