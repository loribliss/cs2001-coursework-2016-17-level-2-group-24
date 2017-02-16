package group24.materialdesign;

import java.util.HashMap;
import java.util.Map;

import group24.materialdesign.api.RecipeService;

/**
 * Created by simsohi on 07/01/17.
 */

public class RecipeServiceTestImpl implements RecipeService {

    Map<String, String> users = new HashMap<>();
    {
        users.put("simran", "password");
    }

    public boolean login(String email, String password) {
        String pass = users.get(email);
        if (pass == null) {
            // TODO give better feedback: user not found
            return false;
        }
        if (!pass.equals(password)) {
            // TODO give better feedback: incorrect password
            return false;
        }
        return true;
    }
}
