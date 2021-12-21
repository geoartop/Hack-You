package game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

/**
 * Λειτουργική κλάση για το configuration του χάρτη, των μπλοκ και των χαρακτηριστικών αυτών
 *
 * @author panagiotis
 * @version $Id: $Id
 */
public final class TileManager {

    private final GamePanel gp;
    final Tile[] tile;
    final int[][] mapTileNum;

    /**
     * <p>Constructor for TileManager.</p>
     *
     * @param gp a {@link game.GamePanel} object
     */
    public TileManager(GamePanel gp) {
        this.gp = gp;
        tile = new Tile[2];
        mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];

        getTileImage();
        loadMap(String.format("/maps/%s.txt", Levels.getDifficulty()));
    }

    private void getTileImage() {
        setup(0, "/tiles/floor.png", false);
        setup(1, "/tiles/wall.PNG", true);
    }

    /**
     * Κάνουμε scale τις εικόνες των tiles έτσι
     * ώστε να μη χρειάζεται να γίνεται η διαδικασία αυτή μέσα στο gameloop
     * Λόγος : Improved rendering performance
     *
     * @param index     : θέση πίνακα tile
     * @param path      : path του image
     * @param collision : αν το tile θα είναι εμπόδιο ή οχι
     */
    private void setup(int index, String path, boolean collision) {
        try {
            tile[index] = new Tile();
            tile[index].setImage(ImageIO.read(getClass().getResourceAsStream(path)));
            tile[index].setImage(FrameSetter.scaleImage(tile[index].getImage(), gp.tileSize, gp.tileSize));
            tile[index].setCollision(collision);
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
            assert is != null;
            BufferedReader br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
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

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * <p>draw.</p>
     *
     * @param g2 a {@link java.awt.Graphics2D} object
     */
    public void draw(Graphics2D g2) {

        int worldCol = 0;
        int worldRow = 0;

        while (worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {

            int tileNum = mapTileNum[worldCol][worldRow]; // number of tile

            int worldX = worldCol * gp.tileSize;
            int worldY = worldRow * gp.tileSize;
            int screenX = worldX - gp.player.worldx + gp.player.screenX;
            int screenY = worldY - gp.player.worldy + gp.player.screenY;

            // παύση κίνησης της κάμερας στο edge του window
            if (gp.player.screenX > gp.player.worldx)
                screenX = worldX;

            if (gp.player.screenY > gp.player.worldy)
                screenY = worldY;

            int rightOffsetValue = gp.screenWidth - gp.player.screenX;

            if (rightOffsetValue > gp.WorldWidth - gp.player.worldx)
                screenX = gp.screenWidth - (gp.WorldWidth - worldX);

            int bottomOffsetValue = gp.screenHeight - gp.player.screenY;

            if (bottomOffsetValue > gp.WorldHeight - gp.player.worldy)
                screenY = gp.screenHeight - (gp.WorldHeight - worldY);

            if (worldX + gp.tileSize > gp.player.worldx - gp.player.screenX &&
                    worldX - gp.tileSize < gp.player.worldx + gp.player.screenX &&
                    worldY + gp.tileSize > gp.player.worldy - gp.player.screenY &&
                    worldY - gp.tileSize < gp.player.worldy + gp.player.screenY) {
                g2.drawImage(tile[tileNum].getImage(), screenX, screenY, null);
            } else if (gp.player.worldx < gp.player.screenX ||
                    gp.player.worldy < gp.player.screenY ||
                    rightOffsetValue > gp.WorldWidth - gp.player.worldx ||
                    bottomOffsetValue > gp.WorldHeight - gp.player.worldy) {
                g2.drawImage(tile[tileNum].getImage(), screenX, screenY, null);
            }

            worldCol++;

            if (worldCol == gp.maxWorldCol) {
                worldCol = 0;
                worldRow++;
            }
        }

    }
}
