package server.model;

import client.model.Player;
import server.view.ServerView;
import shared.JsonManager;
import shared.SocketActions;
import shared.SocketData;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class DedicatedServer implements Runnable {
   private Socket socket;
   private ServerView serverView;
   private final ArrayList<DedicatedServer> servers;
   private ArrayList<Player> playerList;
   private boolean exit;

    public DedicatedServer(Socket client, ArrayList<DedicatedServer> dedicatedServers, ServerView serverView,
                           ArrayList<Player> playerList) {
        // We get a socket, but we only really care about its streams (which we create here because we know what we want,
        // i.e. we can decide to only use one of the two, or to switch to Object streams)
            this.servers = dedicatedServers;
            this.socket=client;
            this.serverView=serverView;
            this.playerList= playerList;
            this.exit=false;
    }

    @Override
    public void run() {
        try {
            SocketData socketData;
            do {
                socketData = (SocketData) new ObjectInputStream(this.socket.getInputStream()).readObject();
                switch (socketData.getAction()) {
                    case USER_IN -> {
                        this.playerList.add(JsonManager.getPlayer(socketData.getData()));
                       this.serverView.addPlayer(JsonManager.getPlayer(socketData.getData()));
                       this.serverView.addLog("Player " + JsonManager.getPlayer(socketData.getData()).getName() + " has joined the server.");
                       socketData= new SocketData(SocketActions.USER_IN, JsonManager.toJson(this.playerList));
                        broadcastToAll(socketData);

                    }
                    case PLAYER_MOVEMENT -> {
                        this.serverView.addLog(JsonManager.getPlayer(socketData.getData()).getName() + " moved to "
                                + JsonManager.getPlayer(socketData.getData()).getxPosition() + ","
                                + JsonManager.getPlayer(socketData.getData()).getyPosition());
                        broadcastToAll(socketData);
                    }
                    case WIN-> {
                        this.serverView.addLog(JsonManager.getPlayer(socketData.getData()).getName() + " has won!");
                        broadcastToAll(socketData);
                    }
                    case LOSE-> {
                        this.serverView.addLog(JsonManager.getPlayer(socketData.getData()).getName() + " has lost!");
                        broadcastToAll(socketData);
                    }
                    case DISCONNECT -> {
                        this.serverView.removePlayer(JsonManager.getPlayer(socketData.getData()));
                        this.serverView.addLog(JsonManager.getPlayer(socketData.getData()).getName()+" has disconnected");
                        this.playerList.remove(JsonManager.getPlayer(socketData.getData()));
                       // socketData= new SocketData(SocketActions.DISCONNECT, JsonManager.toJson(this.playerList));
                        broadcast(socketData);
                        exit=true;
                    }
                }


            } while (!exit);

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        this.servers.remove(this);
        try{
            this.socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void broadcast(SocketData socketData) {
        for (DedicatedServer s : servers){
            if(s!=this){
            s.sendData(socketData);
            }
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
    private void broadcastToAll(SocketData socketData){
        for (DedicatedServer s : servers){
            //if(s!=this){
                s.sendData(socketData);
           // }
        }
    }
}
