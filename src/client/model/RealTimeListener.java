package client.model;

import client.controller.ClientController;
import client.view.ClientView;
import shared.JsonManager;
import shared.SocketData;


import javax.swing.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class RealTimeListener implements Runnable{

  private final ClientController clientController;

    private boolean continueRunning;

    public RealTimeListener(ClientController clientController) {
        this.clientController= clientController;

        this.continueRunning = true;
    }

    @Override
    public void run() {
        while(continueRunning){
           try{
               SocketData socketData = (SocketData) this.clientController.readSocketDataFromServer().readObject();

               switch (socketData.getAction()){
                   case USER_IN -> {
                       SwingUtilities.invokeLater(() -> {
                           this.clientController.addPlayer(JsonManager.getPlayerList(socketData.getData()));
                           System.out.println("player added to the list");
                       });
                   }
                   case PLAYER_MOVEMENT -> SwingUtilities.invokeLater(() -> {
                           this.clientController.updatePlayerPosition(JsonManager.getPlayer(socketData.getData()));
                       });


                   case DISCONNECT -> {
                       System.out.println("User Disconnected");
                       continueRunning=false;
                   }
               }
           }catch (IOException | ClassNotFoundException e){
               e.printStackTrace();
           }
        }

    }
}
