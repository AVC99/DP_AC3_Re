package client.view;

import client.model.GameMap;

import javax.swing.*;
import java.awt.*;

public class ClientView extends JFrame {
    private JPanel mainPanel;
    private GameMap map;

    public ClientView(GameMap map){
        this.map=map;

        configureWindow();
        configureCenter();
    }

    private void configureCenter() {
        mainPanel=new JPanel();
        this.mainPanel.setLayout(new BorderLayout());


        this.add(mainPanel);
    }

    private void configureWindow() {
        this.setSize(500,500);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("Server Window");
        this.setLocationRelativeTo(null);
    }
}
