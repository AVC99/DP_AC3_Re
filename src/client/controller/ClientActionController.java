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
            case "UP"-> System.out.println("UP");
            case "DOWN"-> System.out.println("DOWN");
            case "LEFT"-> System.out.println("LEFT");
            case "RIGHT"-> System.out.println("RIGHT");
        }

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()){
            case W, UP -> System.out.println("UP");
            case A, LEFT -> System.out.println("LEFT");
            case S, DOWN -> System.out.println("DOWN");
            case D, RIGHT -> System.out.println("RIGHT");
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

