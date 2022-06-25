package server;

import shared.SocketActions;
import shared.SocketData;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class DedicatedServer implements Runnable {
   private Socket socket;
    private final ArrayList<DedicatedServer> servers;

    public DedicatedServer(Socket client, ArrayList<DedicatedServer> dedicatedServers) {
        // We get a socket, but we only really care about its streams (which we create here because we know what we want,
        // i.e. we can decide to only use one of the two, or to switch to Object streams)
            this.servers = dedicatedServers;
            this.socket=client;
    }

    @Override
    public void run() {
        try {
            SocketData socketData;
            do {
                socketData = (SocketData) new ObjectInputStream(this.socket.getInputStream()).readObject();
                switch (socketData.getAction()) {
                    case USER_IN -> {
                        System.out.println("User goes in");
                    }
                }

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
