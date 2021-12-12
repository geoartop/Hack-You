package game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Λειτουργική κλάση για το configuration του χάρτη, των μπλοκ και των χαρακτηριστικών αυτών
 */
public class TileManager {

    GamePanel gp;
    public Tile[] tile;
    public int[][] mapTileNum;

    public TileManager(GamePanel gp) {

        this.gp = gp;

        tile = new Tile[2];
        mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];

        getTileImage();
        loadMap("/maps/map2.txt");
        /* or
        loadMap(String.format("/maps/%s.txt",Levels.difficulty));
        */

    }

    private void getTileImage() {
        try {

            tile[0] = new Tile();
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/floor.png"));

            tile[1] = new Tile();
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/wall.PNG"));
            tile[1].collision = true;

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Μέθοδος φόρτωσης λαβυρίνθου
     *
     * @param FilePath
     */
    private void loadMap(String FilePath) {

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
                if (col == gp.maxWorldCol) {
                    col = 0;
                    row++;
                }
            }
            br.close(); //closing buffered reader

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void draw(Graphics2D g2) {

        int worldCol = 0;
        int worldRow = 0;

        while (worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {

            int tileNum = mapTileNum[worldCol][worldRow]; // number of tile

            int worldX = worldCol * gp.tileSize;
            int worldY = worldRow * gp.tileSize;
            int screenX = worldX - gp.player.x + gp.player.screenX;
            int screenY = worldY - gp.player.y + gp.player.screenY;

            // παύση κίνησης της κάμερας στο edge του window
            if (gp.player.screenX > gp.player.x)
                screenX = worldX;

            if (gp.player.screenY > gp.player.y)
                screenY = worldY;

            int rightOffsetValue = gp.screenWidth - gp.player.screenX;

            if (rightOffsetValue > gp.WorldWidth - gp.player.x)
                screenX = gp.screenWidth - (gp.WorldWidth - worldX);


            int bottomOffsetValue = gp.screenHeight - gp.player.screenY;

            if (bottomOffsetValue > gp.WorldHeight - gp.player.y)
                screenY = gp.screenHeight - (gp.WorldHeight - worldY);


            if (worldX + gp.tileSize > gp.player.x - gp.player.screenX &&
                    worldX - gp.tileSize > gp.player.x + gp.player.screenX &&
                    worldY + gp.tileSize > gp.player.y - gp.player.screenY &&
                    worldY - gp.tileSize > gp.player.y + gp.player.screenY) {
                g2.drawImage(tile[tileNum].image, screenX, screenY, gp.tileSize, gp.tileSize, null);
            }


            g2.drawImage(tile[tileNum].image, screenX, screenY, gp.tileSize, gp.tileSize, null);
            worldCol++;


            if (worldCol == gp.maxWorldCol) {
                worldCol = 0;
                worldRow++;
            }
        }

    }
}
