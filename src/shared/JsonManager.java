package shared;

import client.model.Player;
import com.google.gson.Gson;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

public class JsonManager {

    public static String toJson(Serializable object) {
        return new Gson().toJson(object);
    }
    public static ArrayList<Player>getPlayerList(String json){
        Player[] players= new Gson().fromJson(json,Player[].class);
        ArrayList playerList= new ArrayList();
        Collections.addAll(playerList , players);
        return playerList;
    }

    public static Player getPlayer(String json) {
        return new Gson().fromJson(json, Player.class);
    }
}
