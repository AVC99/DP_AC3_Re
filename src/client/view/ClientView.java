package client.view;

import client.controller.ClientActionController;
import client.model.FilePath;
import client.model.Player;

import javax.swing.*;
import java.awt.*;

public class ClientView extends JFrame {

    private GameMap map;
    private JButton upButton;
    private JButton leftButton;
    private JButton downButton;
    private JButton rightButton;
    private HealthBar healthBar;

    public ClientView(GameMap map) {
        this.map=map;
        configureWindow();
        configureNorth();
        configureCenter();
        configureSouth();
    }



    private void configureNorth() {
        this.healthBar= new HealthBar();
        this.add(healthBar, BorderLayout.NORTH);
    }


    /**
     * Function that configures the center of the window
     */
    private void configureCenter() {

        this.add(this.map, BorderLayout.CENTER);

    }

    /**
     * Function that configures the south of the window
     */
    private void configureSouth() {
        JPanel southPanel = new JPanel();
        southPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc= new GridBagConstraints();

        configureButtons(southPanel, gbc);
        this.add(southPanel,BorderLayout.SOUTH);
        this.revalidate();
    }
    /**
     * Funtion that configures the buttons on the south panel
     * @param southPanel south panel
     * @param gbc GridBagConstraints
     */
    private void configureButtons(JPanel southPanel, GridBagConstraints gbc) {
        gbc.gridx=1; gbc.gridy=0;
        upButton = new JButton(new ImageIcon(FilePath.UP_ARROW_PATH));
        upButton.setFocusable(false);
        southPanel.add(upButton, gbc);

        gbc.gridx=0;gbc.gridy=1;
         leftButton = new JButton(new ImageIcon(FilePath.LEFT_ARROW_PATH));
        leftButton.setFocusable(false);
        southPanel.add(leftButton,gbc);

        gbc.gridx=1;gbc.gridy=1;
        downButton = new JButton(new ImageIcon(FilePath.DOWN_ARROW_PATH));
        downButton.setFocusable(false);
        southPanel.add(downButton,gbc);

        gbc.gridx=2;gbc.gridy=1;
        rightButton = new JButton(new ImageIcon(FilePath.RIGHT_ARROW_PATH));
        rightButton.setFocusable(false);
        southPanel.add(rightButton,gbc);
    }

    /**
     * Function that configures the window
     */
    private void configureWindow() {
        this.setSize(700,800);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("TBOI wannabe multiplayer game");
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());
        this.setVisible(true);
    }

    public void addPlayerToList(Player player) {
        this.map.addPlayerToList(player);
    }

    public void addActionController(ClientActionController actionController) {

        upButton.setActionCommand("UP");
        upButton.addActionListener(actionController);
        leftButton.setActionCommand("LEFT");
        leftButton.addActionListener(actionController);
        downButton.setActionCommand("DOWN");
        downButton.addActionListener(actionController);
        rightButton.setActionCommand("RIGHT");
        rightButton.addActionListener(actionController);
        this.addKeyListener(actionController);
        this.setFocusable(true);
    }
}
