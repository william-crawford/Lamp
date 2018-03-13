package cs2340.edu.gatech.lamp.model;


import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

/**
 * Created by Tucker on 3/12/2018.
 */

public class DatabaseShelter {
    private static DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference();


    public static void writeNewShelter(Shelter shelter) {
        String id = Integer.toString(shelter.getId());
        dbRef.child("shelters").child(id).setValue(shelter.getName());
        dbRef.child("shelters").child(id).setValue(shelter.getLocation());
        dbRef.child("shelters").child(id).child("isFull").setValue(false);
        dbRef.child("shelters").child(id).setValue(shelter.getPhoneNumber());
        dbRef.child("shelters").child(id).setValue(shelter.getImageURL());
        dbRef.child("shelters").child(id).setValue(shelter.getCapacity());
        dbRef.child("shelters").child(id).setValue(shelter.getCurrOccup());
        List<ShelterAttribute> attributesList = shelter.getAttributes();
        for (ShelterAttribute a: attributesList) {
            dbRef.child("shelters").child(id).child("filters").setValue(a);
            dbRef.child("filters").child(a.getName()).setValue(id);
        }
    }
}
