package game;

import javax.imageio.ImageIO;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Objects;

/**
 * <p>Configuration του χάρτη, των μπλοκ και των χαρακτηριστικών αυτών.</p>
 *
 * @author Team Hack-You
 * @version 1.0
 */
public final class TileManager {

    private final GamePanel gp;
    private final Tile[] tile;
    final int[][] mapTileNum;
    private static final SecureRandom random = new SecureRandom();
    private static int level;

    /**
     * <p>Constructor for TileManager.</p>
     *
     * @param gp a {@link game.GamePanel} object
     */
    public TileManager(GamePanel gp) {
        this.gp = gp;
        tile = new Tile[2];
        mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];

        if (!LabyrinthFrame.getRestartStatus()) {
            level = 1 + random.nextInt(2);
        }
        getTileImage();
        System.out.println(level);
        loadMap(String.format("/maps/%s%s.txt", Levels.getDifficulty(), level == 1 ? "" : 2));

    }

    /**
     * <p>Getter for the field <code>level</code>.</p>
     *
     * @return an int
     */
    public static int getLevel() {
        return level;
    }

    /**
     * <p>getTileImage.</p>
     */
    private void getTileImage() {
        setup(0, String.format("/tiles/%sFloor.png", Levels.getDifficulty()), false);
        setup(1, String.format("/tiles/%sWall.png", Levels.getDifficulty()), true);
    }

    /**
     * <p>Κάνουμε scale τις εικόνες των tiles έτσι
     * ώστε να μη χρειάζεται να γίνεται η διαδικασία αυτή μέσα στο gameloop
     * Λόγος : Improved rendering performance</p>
     *
     * @param index     : θέση πίνακα tile
     * @param path      : path του image
     * @param collision : αν το tile θα είναι εμπόδιο ή οχι
     */
    private void setup(int index, String path, boolean collision) {
        try {
            BufferedImage image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(path)));
            image = FrameSetter.scaleImage(image, GamePanel.tileSize, GamePanel.tileSize);
            tile[index] = new Tile(image, collision);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * <p>Μέθοδος φόρτωσης λαβυρίνθου</p>
     *
     * @param FilePath path
     */
    private void loadMap(String FilePath) {
        InputStream is = getClass().getResourceAsStream(FilePath);
        assert is != null;
        try (BufferedReader br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8))) {
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

            int worldX = worldCol * GamePanel.tileSize;
            int worldY = worldRow * GamePanel.tileSize;
            int screenX = worldX - gp.getPlayerWorldx() + gp.getPlayerScreenX();
            int screenY = worldY - gp.getPlayerWorldy() + gp.getPlayerScreenY();

            // παύση κίνησης της κάμερας στο edge του window
            if (gp.getPlayerScreenX() > gp.getPlayerWorldx()) {
                screenX = worldX;
            }

            if (gp.getPlayerScreenY() > gp.getPlayerWorldy()) {
                screenY = worldY;
            }

            int rightOffsetValue = GamePanel.screenWidth - gp.getPlayerScreenX();

            if (rightOffsetValue > gp.WorldWidth - gp.getPlayerWorldx()) {
                screenX = GamePanel.screenWidth - (gp.WorldWidth - worldX);
            }

            int bottomOffsetValue = GamePanel.screenHeight - gp.getPlayerScreenY();

            if (bottomOffsetValue > gp.WorldHeight - gp.getPlayerWorldy()) {
                screenY = GamePanel.screenHeight - (gp.WorldHeight - worldY);
            }

            if (worldX + GamePanel.tileSize > gp.getPlayerWorldx() - gp.getPlayerScreenX() &&
                    worldX - GamePanel.tileSize < gp.getPlayerWorldx() + gp.getPlayerScreenX() &&
                    worldY + GamePanel.tileSize > gp.getPlayerWorldy() - gp.getPlayerScreenY() &&
                    worldY - GamePanel.tileSize < gp.getPlayerWorldy() + gp.getPlayerScreenY()) {
                g2.drawImage(tile[tileNum].getImage(), screenX, screenY, null);
            } else if (gp.getPlayerWorldx() < gp.getPlayerScreenX() ||
                    gp.getPlayerWorldy() < gp.getPlayerScreenY() ||
                    rightOffsetValue > gp.WorldWidth - gp.getPlayerWorldx() ||
                    bottomOffsetValue > gp.WorldHeight - gp.getPlayerWorldy()) {
                g2.drawImage(tile[tileNum].getImage(), screenX, screenY, null);
            }

            worldCol++;

            if (worldCol == gp.maxWorldCol) {
                worldCol = 0;
                worldRow++;
            }
        }

    }

    /**
     * <p>getTileCollision.</p>
     *
     * @param tileNum1 an int
     * @param tileNum2 an int
     * @return a boolean
     */
    public boolean getTileCollision(int tileNum1, int tileNum2) {
        return tile[tileNum1].getCollision() || tile[tileNum2].getCollision();
    }
}