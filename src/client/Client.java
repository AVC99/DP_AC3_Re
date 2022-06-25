package client;

import client.view.ClientView;
import shared.NetworkConstants;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
    private GameMap map;
    private ClientView clientView;
    public Client(){


    }
    public static  void main(String[] args){
        try{
            Socket server= new Socket(NetworkConstants.IP, NetworkConstants.PORT);
            Client client= new Client();

        }catch ( IOException e){// TODO check if UnknownHostException is needed here
            e.printStackTrace();
        }
    }
}
