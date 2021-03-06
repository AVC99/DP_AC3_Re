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

    /**
     * Refreshes the players panel
     */
    private void refreshPlayers(){
        this.playerPanel.removeAll();
        if (playerList.size()>0){
            for (Player p:playerList){
                JLabel playerLabel= new JLabel(p.getName());
                playerPanel.add(playerLabel);

            }
        }
        playerScrollPane.revalidate();
        playerScrollPane.repaint();

    }

    /**
     * Refreshes the logs panel
     */
    private void refreshLogPanel(){
        this.logPanel.removeAll();
        for (String log: logList){
            JLabel logLabel = new JLabel(log);
            logPanel.add(logLabel);
            logScrollPane.revalidate();
            logScrollPane.repaint();
        }
    }

    /**
     * Adds log to the log panel
     * @param log log to be added
     */
    public void addLog(String log){
        this.logList.add(log);
        refreshLogPanel();
   }

    /**
     * Adds player to the player panel
     * @param player player to be added
     */
    public void addPlayer(Player player) {
        this.playerList.add(player);
        refreshPlayers();
    }

    /**
     * Configures left side of the view
     */
    private void configureLeft() {
        logPanel= new JPanel();
        logPanel.setLayout(new BoxLayout(logPanel,BoxLayout.Y_AXIS));
        logPanel.setBorder(BorderFactory.createTitledBorder("Logs"));
        logScrollPane= new JScrollPane(logPanel);
        add(logScrollPane);
        revalidate();
    }

    /**
     * Configures right side of the view
     */
    private void configureRight() {
        playerPanel= new JPanel();
        playerPanel.setLayout(new BoxLayout(playerPanel,BoxLayout.Y_AXIS));
        playerPanel.setBorder(BorderFactory.createTitledBorder("Connected Players"));
        playerScrollPane= new JScrollPane(playerPanel);
        add( playerScrollPane);
        revalidate();
    }

    /**
     * Configures the window
     */
    private void configureWindow() {
        this.setSize(500,300);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("Server Window");
        this.setLocationRelativeTo(null);
        this.setLayout(new GridLayout(1,2));
    }

    /**
     * Removes player from the player panel
     * @param player player to be removed
     */
    public void removePlayer(Player player) {
        this.playerList.removeIf(p -> p.getName().equals(player.getName()));
        refreshPlayers();
    }
}
