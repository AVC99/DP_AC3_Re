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

    /**
     * Reads all the messages from the server and calls client controller methods for each action
     */
    @Override
    public void run() {
        while(continueRunning){
           try{

               SocketData socketData = (SocketData) this.clientController.readSocketDataFromServer().readObject();

               switch (socketData.getAction()){
                   case USER_IN -> SwingUtilities.invokeLater(() ->
                           this.clientController.addPlayer(JsonManager.getPlayerList(socketData.getData()))
                       );

                   case PLAYER_MOVEMENT -> SwingUtilities.invokeLater(() ->
                           this.clientController.updatePlayerPosition(JsonManager.getPlayer(socketData.getData()))
                       );


                   case DISCONNECT -> SwingUtilities.invokeLater(()->
                       this.clientController.removePlayer(JsonManager.getPlayer(socketData.getData()))
                   );


               }
           }catch (IOException | ClassNotFoundException e){

               e.printStackTrace();
               this.stop();
           }
        }

    }

    /**
     * Stops the thread from running
     */
    public  void stop(){
        continueRunning=false;
    }
}
