package cs2340.edu.gatech.lamp.model;

/**
 * Created by will on 2/11/18.
 */

public class LoginValidator {

    public enum loginResult {
        HOMELESS,
        SHELTER,
        ADMIN,
        DENIED
    }

    public static loginResult processLogin(String username, String password) {
        return loginResult.DENIED;
    }

}
