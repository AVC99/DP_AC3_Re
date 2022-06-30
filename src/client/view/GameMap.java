package client.view;

import client.model.Enemy;
import client.utils.CellType;
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

    private ArrayList<Player> playerList;

    private final int xSize;
    private final int ySize;

    private BufferedImage image;
    private BufferedImage playerImage;


    public GameMap(char[][] gameBoard, int sizeX, int sizeY) {
        this.gameBoard=gameBoard;
        this.xSize= sizeX;
        this.ySize=sizeY;

        this.playerList= new ArrayList<>();

        setImages();
    }

    /**
     * Function that switches the image to paint depending on the  type of cell
     * @param cell cell
     */
    private void changeCellPath(char cell){
        try{
            switch (cell){
                case CellType.WALL-> image=ImageIO.read(new File(FilePath.WALL_PATH));
                case CellType.EMPTY -> image=ImageIO.read(new File(FilePath.EMPTY_PATH));
                case CellType.SPIKES-> image=ImageIO.read(new File(FilePath.SPIKES_PATH));
                case CellType.START-> image=ImageIO.read(new File(FilePath.START_PATH));
                case CellType.FINISH-> image=ImageIO.read(new File(FilePath.VICTORY_PATH));

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

                for(Player p: playerList){
                    if(j==p.getxPosition() && i==p.getyPosition()){
                        g.drawImage(playerImage,i*cellWidth,j*cellHeight,cellWidth,cellHeight,null);

                        g.drawString(p.getName(),i*cellWidth+3,j*cellHeight+3);
                    }
                }

            }
        }
    }

    /**
     * Getter of the game board
     * @return gameBoard
     */
    public char[][] getGameBoard() {
        return gameBoard;
    }

    /**
     * Sets the player image and the enemies image
     */
    private void setImages() {
        setPlayerImage();
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
                if (this.gameBoard[i][j]==CellType.START){
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
                if (this.gameBoard[i][j]==CellType.START){
                    //xStart=i;
                    return i;
                }
            }
        }
        return 0;
    }

    /**
     * Adds player to the playerList
     * @param player player to add
     */
    public void addPlayerToList(Player player) {
        this.playerList.add(player);
    }

    /**
     * Updates players positions
     * @param player Player to update
     */
    public void updatePlayerList(Player player) {
         for(Player p: playerList){
            if(p.getName().equals(player.getName())){
                p.setPosition(player.getxPosition(), player.getyPosition());
            }
        }
    }

    /**
     * Removes player from the playerList
     * @param player player that has to be removed
     */
    public void removePlayer(Player player) {
        int i=0;
        for(Player p : playerList){
            if (p.getName().equals(player.getName())) {
                this.playerList.remove(i);
                break;
            }
            i++;
        }
    }
}
