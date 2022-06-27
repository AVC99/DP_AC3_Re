package shared;

import client.model.Player;
import com.google.gson.Gson;

import java.io.Serializable;

public class JsonManager {

    public static String toJson(Serializable object) {
        return new Gson().toJson(object);
    }

    public static Player getPlayer(String json) {
        return new Gson().fromJson(json, Player.class);
    }
}
