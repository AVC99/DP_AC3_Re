package server;

import server.controller.ServerController;
import server.view.ServerView;
import shared.NetworkConstants;

import java.io.IOException;
import java.net.ServerSocket;


public class Server {
    public static void main(String[] args)  {

        try {
            ServerSocket serverSocket = new ServerSocket(NetworkConstants.PORT);

            ServerView serverView= new ServerView();
            serverView.setVisible(true);

            ServerController serverController= new ServerController(serverView, serverSocket);
            serverController.startServer();

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
