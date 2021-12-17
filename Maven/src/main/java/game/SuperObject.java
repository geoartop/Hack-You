package game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Δημιουργία αντικειμένων στο παιχνίδι
 */
public abstract class SuperObject {
    public BufferedImage image;
    public String name;
    public boolean collision = false;
    public int worldX, worldY;
    public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
    public int solidAreaDefaultX = 0;
    public int solidAreaDefaultY = 0;

    public SuperObject(String path,int width_height) {
        try {
            image = ImageIO.read(getClass().getResourceAsStream(path));
            image = FrameSetter.scaleImage(image, width_height, width_height);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //public abstract void playSE();

    public void draw(Graphics2D g2, GamePanel gp) {
        int screenX = worldX - gp.player.worldx + gp.player.screenX;
        int screenY = worldY - gp.player.worldy + gp.player.screenY;

        if (gp.player.screenX > gp.player.worldx)
            screenX = worldX;

        if (gp.player.screenY > gp.player.worldy)
            screenY = worldY;

        int rightOffsetValue = gp.screenWidth - gp.player.screenX;

        if (rightOffsetValue > gp.WorldWidth - gp.player.worldx)
            screenX = gp.screenWidth - (gp.WorldWidth - worldX);

        int bottomOffsetValue = gp.screenHeight - gp.player.screenY;

        if (bottomOffsetValue > gp.WorldHeight - gp.player.worldy) {
            screenY = gp.screenHeight - (gp.WorldHeight - worldY);
        }
        if (worldX + gp.tileSize > gp.player.worldx - gp.player.screenX &&
                worldX - gp.tileSize < gp.player.worldx + gp.player.screenX &&
                worldY + gp.tileSize > gp.player.worldy - gp.player.screenY &&
                worldY - gp.tileSize < gp.player.worldy + gp.player.screenY) {

            g2.drawImage(image, screenX, screenY, null);
            // If player is around the edge, draw everything
        } else if (gp.player.worldx < gp.player.screenX ||
                gp.player.worldy < gp.player.screenY ||
                rightOffsetValue > gp.WorldWidth - gp.player.worldx ||
                bottomOffsetValue > gp.WorldHeight - gp.player.worldy) {
            g2.drawImage(image, screenX, screenY, null);
        }


    }
}

