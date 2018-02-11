package cs2340.edu.gatech.lamp.model;

import java.util.HashMap;
import java.util.Map;

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
        Map<String, String> homelessMap = new HashMap<>();
        Map<String, String> shelterMap = new HashMap<>();
        Map<String, String> adminMap = new HashMap<>();
        homelessMap.put("homeless", "qwerty");
        shelterMap.put("shelter", "qwerty");
        adminMap.put("admin", "qwerty");
        if (password.equals(homelessMap.get(username))) {
            return loginResult.HOMELESS;
        }
        if (password.equals(shelterMap.get(username))) {
            return loginResult.SHELTER;
        }
        if (password.equals(homelessMap.get(username))) {
            return loginResult.ADMIN;
        }

        return loginResult.DENIED;
    }

}
