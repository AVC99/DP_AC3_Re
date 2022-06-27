package client.controller;

import client.model.Player;
import client.model.RealTimeListener;
import client.view.ClientView;

import java.net.Socket;
import java.util.ArrayList;

public class ClientController {

    private Socket socket;
    private Player player;
    private ClientView clientView;
    private ArrayList<Player> playerList;


    public ClientController(Player player, Socket socket, ClientView clientView){
        this.player= player;
        this.socket=socket;
        this.clientView=clientView;
        this.playerList= new ArrayList<>();
    }
}
