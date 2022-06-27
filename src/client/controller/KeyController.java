package client.controller;

import client.view.ClientView;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyController implements KeyListener {
    private ClientView view;
    private final static int W = 87;
    private final static int A = 65;
    private final static int S = 83;
    private final static int D = 68;
    private final static int UP = 38;
    private final static int DOWN = 40;
    private final static int LEFT = 37;
    private final static int RIGHT = 39;

    public KeyController(ClientView view) {
        this.view = view;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    /**
     * Function that controls the actions that have to be made if a key is pressed
     * @param e event
     */
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


}
