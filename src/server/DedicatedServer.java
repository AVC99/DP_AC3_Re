package server;

import client.model.Player;
import server.view.ServerView;
import shared.JsonManager;
import shared.SocketActions;
import shared.SocketData;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class DedicatedServer implements Runnable {
   private Socket socket;
   private ServerView serverView;
   private final ArrayList<DedicatedServer> servers;
   private ArrayList<Player> playerList;

    public DedicatedServer(Socket client, ArrayList<DedicatedServer> dedicatedServers, ServerView serverView) {
        // We get a socket, but we only really care about its streams (which we create here because we know what we want,
        // i.e. we can decide to only use one of the two, or to switch to Object streams)
            this.servers = dedicatedServers;
            this.socket=client;
            this.serverView=serverView;
            this.playerList= new ArrayList<>();
    }

    @Override
    public void run() {
        try {
            SocketData socketData;
            do {
                socketData = (SocketData) new ObjectInputStream(this.socket.getInputStream()).readObject();
                switch (socketData.getAction()) {
                    case USER_IN -> {
                       this.serverView.addPlayer(JsonManager.getPlayer(socketData.getData()));
                       this.serverView.addLog("Player " + JsonManager.getPlayer(socketData.getData()).getName() + " has joined the server.");

                    }
                    case PLAYER_MOVEMENT ->
                        this.serverView.addLog(JsonManager.getPlayer(socketData.getData()).getName() + " moved to "
                                + JsonManager.getPlayer(socketData.getData()).getxPosition() + ","
                                + JsonManager.getPlayer(socketData.getData()).getyPosition());


                    case WIN-> {
                        System.out.println("Player won");
                        this.serverView.addLog(JsonManager.getPlayer(socketData.getData()).getName() + " has won!");
                    }
                    case LOSE-> this.serverView.addLog(JsonManager.getPlayer(socketData.getData()).getName() + " has lost!");
                }
                broadcast(socketData);

            } while (!socketData.getAction().equals(SocketActions.DISCONNECT));

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    private void sendData(SocketData socketData){
        try{
            ObjectOutputStream objectOutputStream= new ObjectOutputStream(this.socket.getOutputStream());
            objectOutputStream.writeObject(socketData);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    private void broadcast(SocketData socketData){
        for (DedicatedServer s : servers){
            if(s!=this){
                s.sendData(socketData);
            }
        }
    }
}
