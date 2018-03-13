package cs2340.edu.gatech.lamp.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by JermiahRussell on 2/13/2018.
 */

public class Model {
    private static final Model _instance = new Model();

    public static Model getInstance() {
        return _instance;
    }

    private List<Shelter> allShelters = initShelters();


    private Model() {
    }

    public List<Shelter> getAllShelters() {
        return allShelters;
    }

    private List<Shelter> initShelters() {
        for (Shelter s: Shelter.shelterList) {
            double lat = Double.parseDouble(s.getInfo()[5]);
            double lon = Double.parseDouble(s.getInfo()[4]);
            String address = s.getInfo()[6];
            s.setImageURL("https://assets.rbl.ms/13910706/980x.jpg");
            s.setLocation(new Location(lat, lon, address));
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

        return Shelter.shelterList;
    }
}
