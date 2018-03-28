package cs2340.edu.gatech.lamp.model;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import cs2340.edu.gatech.lamp.R;
import cs2340.edu.gatech.lamp.utils.ShelterManager;

/**
 * Created by JermiahRussell on 2/13/2018.
 */

public class Model {
    private static final Model _instance = new Model();

    public static Model getInstance() {
        return _instance;
    }

    private Model() {}

    private User currentUser;
    private ShelterOwner shelterOwnerDefault;
    private List<Shelter> allShelters;

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public void setShelterOwnerDefault(ShelterOwner owner) {
        shelterOwnerDefault = owner;
    }

    public List<Shelter> getAllShelters() {
        return allShelters;
    }

    public void initShelters(Context context) {
        allShelters = new ArrayList<>();
        Scanner scan = new Scanner(context.getResources().openRawResource(R.raw.homeless_shelter_database));
        scan.nextLine().split(","); //gets rid of the first line with names of columns
        while (scan.hasNext()) {
            String[] info = scan.nextLine().split(",");
            allShelters.add(new Shelter(info));
            ShelterManager.createShelter(shelterOwnerDefault, info);
        }

//        data.add(new Shelter(
//                "The CULC",
//                new Location(33.7749, -84.3964, "266 4th St. NW"),
//                false,
//                "7703643572",
//                "https://image.ibb.co/d3qhSc/CULC300x300.png"
//        ));
//
//        data.add(new Shelter(
//                "Architecture Roof",
//                new Location(33.7761, -84.3960, "245 4th St. NW"),
//                true,
//                "5555555565",
//                null
//        ));


    }
}
