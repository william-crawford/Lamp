package cs2340.edu.gatech.lamp.model;

/**
 * Created by JermiahRussell on 2/13/2018.
 */

class Model {
    private static final Model _instance = new Model();

    static Model getInstance() {
        return _instance;
    }

    private Model() {
    }
}
