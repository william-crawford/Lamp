package cs2340.edu.gatech.lamp.utils;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import cs2340.edu.gatech.lamp.model.Shelter;
import cs2340.edu.gatech.lamp.model.ShelterOwner;

import static android.content.ContentValues.TAG;

/**
 * Created by Tucker on 3/27/2018.
 */

public class ShelterManager {
    public static void createShelter(ShelterOwner user,
                                     String[] inputArgs) {
        user.addShelterID(inputArgs[0]);
        DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference();
        Shelter shelter = new Shelter(inputArgs);
        dbRef.child("shelters").child(inputArgs[0]).setValue(shelter);
    }

    private static Shelter shel;
    public static Shelter getShelterByKey(String key) {
        DatabaseReference dbUniqShelterRef = FirebaseDatabase.getInstance().getReference("/Shelters/" + key + "/");
        dbUniqShelterRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dS) {
                shel = dS.getValue(Shelter.class);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
            }
        });
        return shel;
    }
}
