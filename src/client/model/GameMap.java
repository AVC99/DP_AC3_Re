package client.model;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class GameMap {
    private char[][] gameBoard;
    private Player player;
    //private LinkedList<Player> playerList;

    private final int xSize;
    private final int ySize;


    private BufferedImage image;
    private BufferedImage playerImage;
    private BufferedImage spiderImage;
    private BufferedImage flyImage;

    private ArrayList<Enemy> enemyList;

    public GameMap(char[][] gameBoard, int playerPositionX, int playerPositionY, int sizeX, int sizeY, ArrayList<Enemy> enemyList) {
        this.gameBoard=gameBoard;
        this.xSize= sizeX;
        this.ySize=sizeY;
        this.enemyList=enemyList;
        
        setImages();
    }
    public GameMap(char[][] gameBoard, int playerPositionX, int playerPositionY, int sizeX, int sizeY, ArrayList<Enemy> enemyList,Player player) {
        this.gameBoard=gameBoard;
        this.player= player;
        this.xSize= sizeX;
        this.ySize=sizeY;
        this.enemyList=enemyList;

        setImages();

    }


    private void setImages() {
        setPlayerImage();
        setSpiderImage();
        setFlyImage();
    }

    private void setSpiderImage() {
        try{
            spiderImage= ImageIO.read(new File(FilePath.SPIDER_PATH));
        }catch (IOException e){
            e.printStackTrace();
            System.out.println("Could not find the Player image");
        }
    }
    private void setFlyImage(){
        try{
            spiderImage= ImageIO.read(new File(FilePath.FLY_PATH));
        }catch (IOException e){
            e.printStackTrace();
            System.out.println("Could not find the Player image");
        }

    }

    /**
     * Function that sets the buffered image to the player image
     */
    private void setPlayerImage() {
        try{
            playerImage= ImageIO.read(new File(FilePath.PLAYER_PATH));
        }catch (IOException e){
            e.printStackTrace();
            System.out.println("Could not find the Player image");
        }
    }

    /**
     * Getter that gets the y starting position
     * @return y starting position
     */
    public int getStartY(){
        for (int i=0;i<xSize;i++){
            for (int j=0; j<ySize;j++){
                if (this.gameBoard[i][j]=='S'){
                    //startY=j;
                    return j;
                }
            }
        }
        return 0;
    }
    /**
     * Getter that gets the x starting position
     * @return x starting position
     */
    public int getStartX(){
        for (int i=0;i<xSize;i++){
            for (int j=0; j<ySize;j++){
                if (this.gameBoard[i][j]=='S'){
                    //xStart=i;
                    return i;
                }
            }
        }
        return 0;
    }

}
