package server.controller;

import client.model.Player;
import server.model.DedicatedServer;
import server.view.ServerView;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ServerController {
    private ServerView serverView;
    private ArrayList<DedicatedServer> dedicatedServers;
    private ServerSocket socket;
    private ArrayList<Player> playerList;

    public ServerController(ServerView serverView, ServerSocket socket) {
        this.serverView=serverView;
        this.socket=socket;
        this.dedicatedServers= new ArrayList<>();
        this.playerList= new ArrayList<>();
    }

    /**
     * Starts the server
     */
    public void startServer(){
        System.out.println("Server listening...");
        try{

            while(true){
                Socket client= socket.accept();

                DedicatedServer dedicatedServer= new DedicatedServer(client,dedicatedServers, serverView,playerList);
                dedicatedServers.add(dedicatedServer);
                new Thread(dedicatedServer).start();
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
