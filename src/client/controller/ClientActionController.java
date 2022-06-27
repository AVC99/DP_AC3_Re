package client.controller;

import java.awt.event.*;

public class ClientActionController implements ActionListener, KeyListener, WindowListener {
    private ClientController clientController;
    private final static int W = 87;
    private final static int A = 65;
    private final static int S = 83;
    private final static int D = 68;
    private final static int UP = 38;
    private final static int DOWN = 40;
    private final static int LEFT = 37;
    private final static int RIGHT = 39;

    public ClientActionController(ClientController clientController) {
        this.clientController = clientController;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()){
            case "UP"-> clientController.moveIsaac("UP");
            case "DOWN"-> clientController.moveIsaac("DOWN");
            case "LEFT"-> clientController.moveIsaac("LEFT");
            case "RIGHT"-> clientController.moveIsaac("RIGHT");
        }

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()){
            case W, UP -> clientController.moveIsaac("UP");
            case A, LEFT -> clientController.moveIsaac("LEFT");
            case S, DOWN -> clientController.moveIsaac("DOWN");
            case D, RIGHT -> clientController.moveIsaac("RIGHT");
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {

    }

    @Override
    public void windowClosed(WindowEvent e) {

    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }
}
