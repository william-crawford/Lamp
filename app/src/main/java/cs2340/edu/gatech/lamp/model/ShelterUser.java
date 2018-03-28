package cs2340.edu.gatech.lamp.model;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tucker on 3/4/2018.
 */

public abstract class ShelterUser extends User{
    protected List<String> shelterIDs;

    protected ShelterUser(FirebaseUser user) {
        super(user);
        shelterIDs = new ArrayList<>();
    }



    public static void createShelter(String name,
                                     Location location,
                                     boolean hasSpace,
                                     String phoneNumber,
                                     String imageURL,
                                     String key) {
        DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference();
        Shelter shelter = new Shelter(name, location, hasSpace, phoneNumber, imageURL, key);
        dbRef.child("shelters").child(key).setValue(shelter);
    }

    public void deleteShelter(){
        //create method later
    }

}
