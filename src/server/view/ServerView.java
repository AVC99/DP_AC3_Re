package server.view;

import javax.swing.*;
import java.awt.*;

public class ServerView extends JFrame {
    private JPanel mainPanel;

    public ServerView(){
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
