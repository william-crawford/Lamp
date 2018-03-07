package cs2340.edu.gatech.lamp.model;

/**
 * Created by JermiahRussell on 2/14/2018.
 */

public class Password {
    private int password_hash;

    public Password(String password) {
        password_hash = password.hashCode();
    }

}
