package client.model;

import java.io.Serializable;

public class Player implements Serializable {
    private int xPosition;
    private int yPosition;
    private int health;
    private String name;
    
    public Player(String name, int xPosition, int yPosition){
        this.name=name;
        this.health=10;
        this.xPosition=xPosition;
        this.yPosition=yPosition;
    }

    /**
     * Function that makes the player take damage
     * @param damage damage done to the player
     */
    public void takeDamage(int damage){
        this.health-=damage;
    }

    /**
     * Getter of the player's X current position
     * @return player's X current position
     */
    public int getxPosition() {
        return xPosition;
    }

    /**
     * Getter of the player's Y current position
     * @return player's Y current position
     */
    public int getyPosition() {
        return yPosition;
    }

    /**
     * Getter of the player's health
     * @return current player's health
     */
    public int getHealth() {
        return health;
    }

    /**
     * Getter of the player's name
     * @return player's name
     */
    public String getName() {
        return name;
    }

    /**
     * Resets the player's health back to full
     */
    public void resetHealth(){
        this.health=10;
    }


    /**
     * Sets the player position to the desired position
     * @param gotoX desired position X
     * @param gotoY desired position y
     */
    public void setPosition(int gotoX, int gotoY) {
        this.xPosition=gotoX;
        this.yPosition=gotoY;
    }
}
