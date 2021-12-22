package game;

import javax.imageio.ImageIO;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Δημιουργία αντικειμένων στο παιχνίδι
 *
 * @author Team Hack-You
 * @version 1.0
 */
public abstract class SuperObject {

    protected BufferedImage image;
    protected String name;
    protected boolean collision = false;
    protected int worldX, worldY;
    protected final Rectangle solidArea
            = new Rectangle(0, 0, 48, 48);
    protected final int solidAreaDefaultX = 0;
    protected final int solidAreaDefaultY = 0;

    protected final Sound se = new Sound();

    /**
     * <p>Constructor for SuperObject.</p>
     *
     * @param path a {@link java.lang.String} object
     * @param width_height a int
     */
    public SuperObject(String path, int width_height) {
        try {
            image = ImageIO.read(getClass().getResourceAsStream(path));
            image = FrameSetter.scaleImage(image, width_height, width_height);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * <p>Default Constructor for SuperObject.</p>
     */
    public SuperObject() {}

    //TODO(g.artop) Fix draw problem
    void setValues(Graphics2D g2, GamePanel gp, BufferedImage image) {
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

        if (bottomOffsetValue > gp.WorldHeight - gp.player.worldy)
            screenY = gp.screenHeight - (gp.WorldHeight - worldY);

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

    /**
     * <p>play Sound Effect.</p>
     */
    public abstract void playSE();

    /**
     * <p>draw.</p>
     *
     * @param g2 a {@link java.awt.Graphics2D} object
     * @param gp a {@link game.GamePanel} object
     */
    public abstract void draw(Graphics2D g2, GamePanel gp);

}

