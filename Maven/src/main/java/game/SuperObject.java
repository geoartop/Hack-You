package game;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Υπερκλάση για τη δημιουργία αντικειμένων στο παιχνίδι
 */
public class SuperObject {
    public BufferedImage image;
    public String name;
    public boolean collision = false;
    public int worldX, worldY;
    public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
    public int solidAreaDefaultX = 0;
    public int solidAreaDefaultY = 0;

    public void draw(Graphics2D g2, GamePanel gp) {
        int screenX = worldX - gp.player.x + gp.player.screenX;
        int screenY = worldY - gp.player.y + gp.player.screenY;

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
            g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
        }


        g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
    }
}

