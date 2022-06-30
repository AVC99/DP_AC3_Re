package client.model.persistance;

import client.model.*;
import client.utils.FilePath;
import client.view.GameMap;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class MapDAO {
    /**
     *Function that reads a .txt File and creates an initial GameMap
     * @return the Initial GameMap
     */
    public GameMap startGameMap(){
        GameMap gameMap= null;
        try{
            String mapText= new String(Files.readAllBytes(Path.of(FilePath.MAP_PATH)));
            String[] lines= mapText.split(System.lineSeparator());

            int sizeX= Integer.parseInt(lines[0]);
            int sizeY= Integer.parseInt(lines[1]);

            char[][] gameBoard = new char[sizeX][sizeY];

            for(int i=2;i<lines.length;i++){// Lines
                int j=0;
                for (char c: lines[i].toCharArray()){// Individual character
                    gameBoard[i-2][j]=c;
                    j++;
                }
            }
            gameMap= new GameMap(gameBoard,sizeX,sizeY);

        }catch (IOException| NumberFormatException e){
            e.printStackTrace();
            System.out.println("Could not find or load the game File correctly!");
        }

        return gameMap;
    }
}
