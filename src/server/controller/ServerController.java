package server.controller;

import server.DedicatedServer;
import server.view.ServerView;
import shared.NetworkConstants;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ServerController {
    private ServerView serverView;
    private ArrayList<DedicatedServer> dedicatedServers;
    private ServerSocket socket;

    public ServerController(ServerView serverView) {
        this.serverView=serverView;
        this.dedicatedServers= new ArrayList<>();
    }

    public void startServer(){
        System.out.println("Server listening...");

        try{
            this.socket= new ServerSocket(NetworkConstants.PORT);
            while(true){
                Socket client= socket.accept();

                DedicatedServer dedicatedServer= new DedicatedServer(client,dedicatedServers);
                dedicatedServers.add(dedicatedServer);
                new Thread(dedicatedServer).start();
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
