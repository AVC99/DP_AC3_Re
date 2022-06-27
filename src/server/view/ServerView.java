package server.view;

import client.model.Player;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ServerView extends JFrame {

    private JPanel playerPanel;
    private JPanel logPanel;
    private JScrollPane playerScrollPane;
    private JScrollPane logScrollPane;
    private ArrayList<Player> playerList;
    private ArrayList<String> logList;


    public ServerView(){
        this.playerList= new ArrayList<>();
        this.logList= new ArrayList<>();
        configureWindow();
        configureRight();
        configureLeft();
    }

    private void refreshPlayers(){
        this.playerPanel.removeAll();
        for (Player p:playerList){
            JLabel playerLabel= new JLabel(p.getName());
            playerPanel.add(playerLabel);
            playerScrollPane.revalidate();
            playerScrollPane.repaint();
        }
    }

    private void refreshLogPanel(){
        this.logPanel.removeAll();
        for (String log: logList){
            JLabel logLabel = new JLabel(log);
            logPanel.add(logLabel);
            logScrollPane.revalidate();
            logScrollPane.repaint();
        }
    }


   private void addLog(String log){
        this.logList.add(log);
        refreshLogPanel();
   }

    private void addPlayer(Player player) {
        player.setName("Test");
        this.playerList.add(player);
        refreshPlayers();

    }

    private void configureLeft() {
        logPanel= new JPanel();
        logPanel.setLayout(new BoxLayout(logPanel,BoxLayout.Y_AXIS));
        logPanel.setBorder(BorderFactory.createTitledBorder("Movements"));
        logScrollPane= new JScrollPane(logPanel);
        add(logScrollPane);
        revalidate();
    }

    private void configureRight() {
        playerPanel= new JPanel();
        playerPanel.setLayout(new BoxLayout(playerPanel,BoxLayout.Y_AXIS));
        playerPanel.setBorder(BorderFactory.createTitledBorder("Players"));
        playerScrollPane= new JScrollPane(playerPanel);
        add( playerScrollPane);
        revalidate();
    }

    private void configureWindow() {
        this.setSize(500,300);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("Server Window");
        this.setLocationRelativeTo(null);
        this.setLayout(new GridLayout(1,2));
    }
}
