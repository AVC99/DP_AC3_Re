package client.view;

import client.model.Enemy;
import client.utils.FilePath;
import client.model.Fly;
import client.model.Player;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class GameMap extends JPanel {

    private char[][] gameBoard;
    private Player player;
    private ArrayList<Player> playerList;

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
        this.playerList= new ArrayList<>();

        setImages();
    }
    public GameMap(char[][] gameBoard, int playerPositionX, int playerPositionY, int sizeX, int sizeY, ArrayList<Enemy> enemyList,Player player) {
        this.gameBoard=gameBoard;
        this.player= player;
        this.xSize= sizeX;
        this.ySize=sizeY;
        this.enemyList=enemyList;
        this.playerList= new ArrayList<>();

        setImages();

    }
    private void changeCellPath(char cell){
        try{
            switch (cell){
                case'#'-> image=ImageIO.read(new File(FilePath.WALL_PATH));
                case ' ', '-', '|' -> image=ImageIO.read(new File(FilePath.EMPTY_PATH));
                case 'X'-> image=ImageIO.read(new File(FilePath.SPIKES_PATH));
                case 'S'-> image=ImageIO.read(new File(FilePath.START_PATH));
                case 'W'-> image=ImageIO.read(new File(FilePath.VICTORY_PATH));

            }
        }catch (IOException e){
            e.printStackTrace();
            System.out.println("Could not find the cell image");
        }

    }
    /**
     * Function that iterates the map and paints it according to each cell
     * @param g Graphics
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int cellHeight= this.getHeight()/ySize;
        int cellWidth= this.getWidth()/xSize;
        for (int i=0;i<xSize;i++){
            for (int j=0; j<ySize;j++){
                changeCellPath(gameBoard[j][i]);
                g.drawImage(image,i*cellWidth,j*cellHeight,cellWidth,cellHeight,null);
                for(Enemy e: enemyList){
                    if(j==e.getXPosition() && i==e.getYPosition()){
                        if(e instanceof Fly){
                            g.drawImage(flyImage,i*cellWidth,j*cellHeight,cellWidth,cellHeight,null);

                        }else g.drawImage(spiderImage,i*cellWidth,j*cellHeight,cellWidth,cellHeight,null);


                    }
                }
                for(Player p: playerList){
                    if(j==p.getxPosition() && i==p.getyPosition()){
                        g.drawImage(playerImage,i*cellWidth,j*cellHeight,cellWidth,cellHeight,null);

                        g.drawString(p.getName(),i*cellWidth+3,j*cellHeight+3);
                    }
                }

            }
        }
    }

    public char[][] getGameBoard() {
        return gameBoard;
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
            flyImage= ImageIO.read(new File(FilePath.FLY_PATH));
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

    public void addPlayerToList(Player player) {
        this.playerList.add(player);
    }

    public void updatePlayerList(Player player) {
         for(Player p: playerList){
            if(p.getName().equals(player.getName())){
                p.setPosition(player.getxPosition(), player.getyPosition());
                System.out.println(p.getName()+" Moves");
            }
        }
    }
}
