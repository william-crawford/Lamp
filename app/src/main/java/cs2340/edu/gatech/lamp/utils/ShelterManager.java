package cs2340.edu.gatech.lamp.utils;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

import cs2340.edu.gatech.lamp.model.Shelter;
import cs2340.edu.gatech.lamp.model.ShelterOwner;

import static android.content.ContentValues.TAG;

/**
 * Created by Tucker on 3/27/2018.
 */

public class ShelterManager {
    /**
     * Creates a Shelter
     * @param user ShelterOwner for a shelter
     * @param inputArgs information to set a Shelter's attributes
     */
    public static void createShelter(ShelterOwner user,
                                     String[] inputArgs) {
        user.addShelterID(inputArgs[0]);
        DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference();
        Shelter shelter = new Shelter(inputArgs);
        dbRef.child("shelters").child(shelter.getKey()).setValue(shelter);
        dbRef.child("isFull").child(shelter.getKey()).setValue((shelter.isFull()) ? (true) : (null));
    }

    /**
     * Updates a Shelter
     * @param shelter a Shelter to update
     */
    public static void updateShelter(Shelter shelter) {
        DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference();
        dbRef.child("shelters").child(shelter.getKey()).setValue(shelter);
        dbRef.child("isFull").child(shelter.getKey()).setValue((shelter.isFull()) ? (true) : (null));

    }

    private static Shelter shel;

    /**
     * Gives a Shelter by its key
     * @param key the key of a Shelter
     * @return returns a Shelter
     */
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
