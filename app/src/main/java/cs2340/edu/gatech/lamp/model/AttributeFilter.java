package cs2340.edu.gatech.lamp.model;

import java.util.List;

/**
 * Created by will on 2/25/18.
 */

public interface AttributeFilter {

    boolean allow(List<ShelterAttribute> attributes);

}
