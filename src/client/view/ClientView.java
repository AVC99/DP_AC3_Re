package client.view;

import client.controller.ButtonController;
import client.controller.KeyController;
import client.model.FilePath;
import client.model.Player;

import javax.swing.*;
import java.awt.*;

public class ClientView extends JFrame {

    private GameMap map;
    private ButtonController buttonController;
    private KeyController keyController;
    private HealthBar healthBar;

    public ClientView(GameMap map) {
        this.map=map;
        this.buttonController=new ButtonController(this);
        this.keyController=new KeyController(this);
        configureKeys();
        configureWindow();
        configureNorth();
        configureCenter();
        configureSouth();
    }

    private void configureNorth() {
        this.healthBar= new HealthBar();
        this.add(healthBar, BorderLayout.NORTH);
    }

    private void configureKeys() {
        this.addKeyListener(keyController);
        setFocusable(true);
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
        JButton upButton = new JButton(new ImageIcon(FilePath.UP_ARROW_PATH));
        upButton.setActionCommand("UP");
        upButton.addActionListener(buttonController);
        upButton.setFocusable(false);
        southPanel.add(upButton, gbc);

        gbc.gridx=0;gbc.gridy=1;
        JButton leftButton = new JButton(new ImageIcon(FilePath.LEFT_ARROW_PATH));
        leftButton.setActionCommand("LEFT");
        leftButton.addActionListener(buttonController);
        leftButton.setFocusable(false);
        southPanel.add(leftButton,gbc);

        gbc.gridx=1;gbc.gridy=1;
        JButton downButton = new JButton(new ImageIcon(FilePath.DOWN_ARROW_PATH));
        downButton.setActionCommand("DOWN");
        downButton.addActionListener(buttonController);
        downButton.setFocusable(false);
        southPanel.add(downButton,gbc);

        gbc.gridx=2;gbc.gridy=1;
        JButton rightButton = new JButton(new ImageIcon(FilePath.RIGHT_ARROW_PATH));
        rightButton.setActionCommand("RIGHT");
        rightButton.addActionListener(buttonController);
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
}
