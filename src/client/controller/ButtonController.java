package client.controller;

import client.Client;
import client.view.ClientView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonController implements ActionListener {
    private ClientView view;

    public ButtonController(ClientView view) {
        this.view = view;
    }

    /**
     * Function that controls tha actions performed by the buttons
     * @param e event
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()){

            case "UP"-> System.out.println("UP");
            case "DOWN"-> System.out.println("DOWN");
            case "LEFT"-> System.out.println("LEFT");
            case "RIGHT"-> System.out.println("RIGHT");
        }

    }
}
