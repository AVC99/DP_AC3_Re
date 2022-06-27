package client.model;

public class Player {
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
    public void takeDamage(int damage){
        this.health-=damage;
    }
    public int getxPosition() {
        return xPosition;
    }

    public int getyPosition() {
        return yPosition;
    }

    public int getHealth() {
        return health;
    }

    public String getName() {
        return name;
    }
    public void resetHealth(){
        this.health=10;
    }
    public void move(int gotoX, int gotoY){
        this.xPosition=gotoX;
        this.yPosition=gotoY;
    }

    public void setName(String name) {
        this.name=name;
    }
}
