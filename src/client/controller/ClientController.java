package client.controller;

import client.model.Player;
import client.view.ClientView;
import shared.JsonManager;
import shared.SocketActions;
import shared.SocketData;

import java.io.IOException;
import java.io.ObjectOutputStream;
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

        sendPlayerToServer();
    }

    private void sendPlayerToServer() {
        this.sendSocketDataToServer(SocketActions.USER_IN, JsonManager.toJson(this.player));
        this.clientView.addPlayerToList(this.player);
        clientView.repaint();
    }

    public void sendSocketDataToServer(SocketActions action, String data){

        try{
            SocketData socketData= new SocketData(action, data);
            ObjectOutputStream objectOutputStream= new ObjectOutputStream(this.socket.getOutputStream());
            objectOutputStream.writeObject(socketData);

        }catch (IOException e){
            e.printStackTrace();
            System.out.println("ERROR: Client cannot send a " + action + " socket to the server.");
        }
    }
}
