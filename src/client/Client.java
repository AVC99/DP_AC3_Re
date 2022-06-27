package client;

import client.controller.ClientActionController;
import client.controller.ClientController;
import client.view.GameMap;
import client.model.Player;
import client.model.RealTimeListener;
import client.model.persistance.MapDAO;
import client.view.ClientView;
import shared.NetworkConstants;

import javax.swing.*;
import java.io.IOException;
import java.net.Socket;

public class Client {
    private GameMap map;
    private ClientView clientView;
    private  Socket server;

    public Client(Socket server){
        this.server= server;
    this.map= new MapDAO().startGameMap();

    }
    public static  void main(String[] args){
        try{
           Socket server = new Socket(NetworkConstants.IP, NetworkConstants.PORT);
            Client client= new Client(server);

            client.start();

        }catch ( IOException e){// TODO check if UnknownHostException is needed here
            e.printStackTrace();
        }
    }

    private void start() {
        String playerName= JOptionPane.showInputDialog("Insert the player name: ");
        Player player= new Player(playerName, this.map.getStartX(),this.map.getStartY());
        ClientView clientView= new ClientView(map);
        ClientController clientController= new ClientController(player, server, clientView);

        clientView.addActionController(new ClientActionController(clientController));
        RealTimeListener realtimeListener= new RealTimeListener(clientController);
        realtimeListener.run();
    }
}
