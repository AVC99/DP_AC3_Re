package client.model.persistance;

import client.model.*;
import client.view.GameMap;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class MapDAO {
    /**
     *Function that reads a .txt File and creates an initial Gamemap
     * @return the Initial GameMap
     */
    public GameMap startGameMap(){
        GameMap gameMap= null;
        try{
            String mapText= new String(Files.readAllBytes(Path.of(FilePath.MAP_PATH)));
            //TODO CHANGE /r for /n
            String[] lines= mapText.split("\r\n");


            int sizeX= Integer.parseInt(lines[0]);
            int sizeY= Integer.parseInt(lines[1]);

            int playerPositionX=0;
            int playerPositionY=0;
            char[][] gameBoard = new char[sizeX][sizeY];
            ArrayList<Enemy> enemyList= new ArrayList<>();

            for(int i=2;i<lines.length;i++){//Filas
                int j=0;
                for (char c: lines[i].toCharArray()){//letra individual
                    gameBoard[i-2][j]=c;
                    if(c=='S'){
                        playerPositionX=i-2;
                        playerPositionY=j;
                    }else if(c=='|'){
                        enemyList.add(new Fly(Enemy.FLY_DMG,i-2,j));
                    }else if (c=='-'){
                        enemyList.add(new Spider(Enemy.SPIDER_DMG,i-2,j));
                    }
                    j++;
                }
            }
            gameMap= new GameMap(gameBoard,playerPositionX,playerPositionY,sizeX,sizeY,enemyList);
            /*gameMap.askTotalPlayers();
            gameMap.insertNewPlayer();*/

        }catch (IOException| NumberFormatException e){
            e.printStackTrace();
            System.out.println("Could not find or load the game File correctly!");
        }

        return gameMap;
    }
}
