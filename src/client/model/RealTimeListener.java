package client.model;

import client.controller.ClientController;
import client.view.ClientView;
import shared.SocketData;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

public class RealTimeListener implements Runnable{

  private final ClientController clientController;
    private Socket socket;
    private boolean continueRunning;

    public RealTimeListener(ClientController clientController) {
        this.clientController= clientController;

        this.continueRunning = true;
    }

    @Override
    public void run() {
        while(continueRunning){
           try{
               SocketData socketData = (SocketData) new ObjectInputStream(this.socket.getInputStream()).readObject();
               switch (socketData.getAction()){
                   case USER_IN -> System.out.println("UserIn");
                   case DISCONNECT -> {
                       System.out.println("UserDisconnected");
                       continueRunning=false;
                   }
               }
           }catch (IOException | ClassNotFoundException e){
               e.printStackTrace();
           }
        }

    }
}
