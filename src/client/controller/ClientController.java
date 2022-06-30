package client.controller;

import client.model.Player;
import client.utils.CellType;
import client.view.ClientView;
import shared.JsonManager;
import shared.SocketActions;
import shared.SocketData;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class ClientController {

    private static final int SPIKE_DAMAGE = 1;
    private Socket socket;
    private Player player;
    private ClientView clientView;
    private ArrayList<Player> playerList;


    public ClientController(Player player, Socket socket, ClientView clientView){
        this.player= player;
        this.socket=socket;
        this.clientView=clientView;
        this.playerList= new ArrayList<>();

        addWindowController();
        sendPlayerToServer();
    }

    /**
     * Function that receives the button/key information to move
     * @param direction direction desired to move Isaac
     */
    public void moveIsaac(String direction){
        int gotoX;
        int gotoY;
        switch (direction){
            case "UP"->{
                gotoX=player.getxPosition()-1;
                gotoY=player.getyPosition();
                if(canIsaacMove(gotoX, gotoY)){
                    actuallyMove(gotoX, gotoY);
                }
            }
            case "DOWN"->{
                gotoX=player.getxPosition()+1;
                gotoY=player.getyPosition();
                if(canIsaacMove(gotoX,gotoY)){
                    actuallyMove(gotoX,gotoY);
                }
            }
            case "RIGHT"->{
                gotoX=player.getxPosition();
                gotoY=player.getyPosition()+1;
                if(canIsaacMove(gotoX,gotoY)){
                    actuallyMove(gotoX,gotoY);
                }
            }
            case "LEFT"->{
                gotoX=player.getxPosition();
                gotoY=player.getyPosition()-1;
                if(canIsaacMove(gotoX,gotoY)){
                    actuallyMove(gotoX,gotoY);
                }
            }
        }
    }

    /**
     * Function that tells if the go-to is not a wall or off the map
     * @param x x go-to position
     * @param y y go-to position
     * @return true/false
     */
    private boolean canIsaacMove(int x, int y){
        return x > 0 && x <= 16 && y > 0 && y <= 16 && clientView.getMap().getGameBoard()[x][y] != CellType.WALL;
    }

    /**
     * Function that moves Isaac to the desired position
     * @param gotoX x Position to move
     * @param gotoY y Position to move
     */
    private void actuallyMove(int gotoX, int gotoY) {
        player.setPosition(gotoX, gotoY);
        clientView.repaint();
        sendMovementToServer();
        if(hasIsaacWon(gotoX, gotoY)){
            sendWinToServer();
            createVictoryPane();
            restart();
        }else{
            if(isSpikeCell(gotoX, gotoY)){
               player.takeDamage(SPIKE_DAMAGE);
               clientView.getHealthBar().setValue(player.getHealth());
            }
            if(hasIsaacLost()){
                sendLossToServer();
                createLosingPane();
                restart();
            }
        }

    }

    private void restart() {
        this.player.resetHealth();
        this.player.setPosition(this.clientView.getMap().getStartX(), this.clientView.getMap().getStartY());
        this.clientView.getHealthBar().setValue(this.player.getHealth());
        this.clientView.getMap().repaint();
        this.clientView.repaint();
    }

    /**
     * Adds listener to the Client window so when the player exits it disconnects them from the game and the server
     */
    private void addWindowController(){
        this.clientView.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.clientView.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent event) {
                disconnectFromServer();
                clientView.dispose();
                System.exit(0);
            }
        });
    }

    /**
     * Method that removes player from the game and disconnects them from the server
     */
    private void disconnectFromServer() {
        /*try{
            this.sendSocketDataToServer(SocketActions.DISCONNECT, JsonManager.toJson(this.player));

            this.socket.close();
        }catch (IOException e){
            e.printStackTrace();
            System.out.println("The player "+ this.player.getName()+" could not disconnect correctly");
        }*/
        this.sendSocketDataToServer(SocketActions.DISCONNECT, JsonManager.toJson(this.player));

    }


    /**
     * Function that tells if the go-to position has spikes in it
     * @param x x go-to position
     * @param y y go-to position
     * @return true/false
     */
    private boolean isSpikeCell(int x, int y) {
        return this.clientView.getMap().getGameBoard()[x][y] == CellType.SPIKES;
    }

    private void createLosingPane() {
        JOptionPane.showMessageDialog(null,"YOU LOST","LOST",JOptionPane.ERROR_MESSAGE);
    }

    private void createVictoryPane() {
        JOptionPane.showMessageDialog(null,"WINNER WINNER CHICKEN DINNER","Winner",JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Function that tells if the player has lost all his hp
     * @return boolean
     */
    private boolean hasIsaacLost(){
        return player.getHealth() <= 0;
    }

    /**
     * Function that returns if Isaac has won the Game
     * @param x x go-to position
     * @param y y go-to position
     * @return true/false
     */
    private boolean hasIsaacWon(int x,int y){
        return this.clientView.getMap().getGameBoard()[x][y] == CellType.FINISH;
    }

    /**
     * Sends the player information to the server
     */
    private void sendPlayerToServer() {
        this.sendSocketDataToServer(SocketActions.USER_IN, JsonManager.toJson(this.player));
        this.clientView.addPlayerToList(this.player);
        clientView.repaint();
    }

    /**
     * Sends the movement to the server
     */
    private void sendMovementToServer(){
        this.sendSocketDataToServer(SocketActions.PLAYER_MOVEMENT, JsonManager.toJson(this.player));
    }

    /**
     * Sends the win information to the server
     */
    private void sendWinToServer() {
        this.sendSocketDataToServer(SocketActions.WIN, JsonManager.toJson(this.player));
    }

    /**
     * Function that a player lost to the server
     */
    private void sendLossToServer() {
        this.sendSocketDataToServer(SocketActions.LOSE, JsonManager.toJson(this.player));
    }

    /**
     * Function that sends the socket data to the server
     * @param action SocketAction to send
     * @param data data to send
     */
    public void sendSocketDataToServer(SocketActions action, String data){

        try{
            SocketData socketData= new SocketData(action, data);
            ObjectOutputStream objectOutputStream= new ObjectOutputStream(this.socket.getOutputStream());
            objectOutputStream.writeObject(socketData);

        }catch (IOException e){
            e.printStackTrace();
            System.out.println("ERROR: Client cannot send a " + action + " socket to the server.");
        }
    }



    /**
     * Method that adds players to the game playerList
     * @param serverPlayerList playerList from the server
     */
    public void addPlayer(ArrayList<Player> serverPlayerList) {
        for (Player p: serverPlayerList){
            if(!p.getName().equals(this.player.getName())){
                this.playerList.add(p);
                this.clientView.getMap().addPlayerToList(p);
            }
        }


    }

    /**
     * Function that reads data from the server
     * @return data sent by the server
     * @throws IOException Exception
     */
    public ObjectInputStream readSocketDataFromServer() throws IOException {
        return new ObjectInputStream(this.socket.getInputStream());
    }

    /**
     * Method that updates and repaints the players
     * @param player player
     */
    public void updatePlayerPosition(Player player) {
       this.clientView.getMap().updatePlayerList(player);
        this.clientView.getMap().repaint();
    }

    /**
     * Method that removes a desired player from the playerlist
     * @param player player
     */
    public void removePlayer(Player player) {
       this.playerList.remove(player);
       this.clientView.getMap().removePlayer(player);
       this.clientView.repaint();
        try {
            this.socket.close();
        } catch (IOException e) {
            e.printStackTrace();

        }
    }

}
