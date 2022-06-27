package client.view;

import javax.swing.*;
import java.awt.*;

public class HealthBar extends JProgressBar {
    /**
     * Set up of the HP bar
     */
    public HealthBar(){
        setPreferredSize(new Dimension(200,50));
        setMaximum(10);
        setMinimum(0);
        setValue(10);
        setBackground(Color.red);
        setForeground(Color.green);
        setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
    }
}
