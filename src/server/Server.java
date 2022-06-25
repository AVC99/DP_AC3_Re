package server;

import server.controller.ServerController;
import server.view.ServerView;


public class Server {
    public static void main(String[] args)  {
        ServerView serverView= new ServerView();
        serverView.setVisible(true);

        ServerController serverController= new ServerController(serverView);
        serverController.startServer();

    }
}
