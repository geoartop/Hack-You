package test;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {
    GamePanel gp;
    public Tile[] tile;
    public int[][] mapTileNum;

    public TileManager(GamePanel gp) {

        this.gp = gp;

        tile = new Tile[3];
        mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];

        getTileImage();
        loadMap("/maps/map.txt");
    }

    public void getTileImage() {
        try {

            tile[0] = new Tile();
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/floor.png"));

            tile[1] = new Tile();
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/wall.PNG"));
            tile[1].collision = true;

            tile[2] = new Tile();
            tile[2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/question_mark.png"));

        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void loadMap(String FilePath) {

        try {
            InputStream is = getClass().getResourceAsStream(FilePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while (col < gp.maxWorldCol && row < gp.maxWorldRow) {
                String line = br.readLine();

                while (col < gp.maxWorldCol) {

                    String[] numbers = line.split(" "); //splits the line with spaces

                    int num = Integer.parseInt(numbers[col]); //from String to integer
                    mapTileNum[col][row] = num; //store the integer
                    col++;
                }
                if(col == gp.maxWorldCol) {
                    col = 0;
                    row++;
                }
            }
            br.close(); //closing buffered reader

        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    //draws the tiles
    public void draw(Graphics2D g2) {

        int worldcol = 0;
        int worldrow = 0;

        while (worldcol < gp.maxWorldCol && worldrow < gp.maxWorldRow) {

            int tileNum = mapTileNum[worldcol][worldrow]; // number of tile

            int worldX= worldcol* gp.tileSize;
            int worldY= worldrow*gp.tileSize;
            int ScreenX=worldX-gp.player.worldx+gp.player.screenX;
            int ScreenY=worldY-gp.player.worldy+gp.player.screenY;


            g2.drawImage(tile[tileNum].image,ScreenX,ScreenY,gp.tileSize,gp.tileSize,null);
            worldcol++;


            if (worldcol == gp.maxWorldCol) {
                worldcol = 0;
                worldrow++;

            }
        }

    }
}
