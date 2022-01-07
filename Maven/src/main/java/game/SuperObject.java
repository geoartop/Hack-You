package game;

import javax.imageio.ImageIO;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

/**
 * <p>Δημιουργία αντικειμένων στο παιχνίδι.</p>
 *
 * @author Team Hack-You
 * @version 1.0
 */
public abstract class SuperObject {

    protected BufferedImage image;
    protected String name;
    protected boolean collision = false;
    private int worldX, worldY;
    protected Rectangle solidArea
            = new Rectangle(0, 0, 48, 48);
    static final int solidAreaDefaultX = 0;
    static final int solidAreaDefaultY = 0;

    /**
     * Constant <code>se</code>
     */
    protected static final Sound se = new Sound();

    /**
     * <p>Constructor for SuperObject.</p>
     *
     * @param path         a {@link java.lang.String} object
     * @param width_height an int
     */
    public SuperObject(String path, int width_height) {
        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(path)));
            image = FrameSetter.scaleImage(image, width_height, width_height);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * <p>Default Constructor for SuperObject.</p>
     */
    public SuperObject() {
    }

    /**
     * <p>Συμπλήρωση της μεθόδου draw για SuperObject.</p>
     *
     * @param g2    a {@link Graphics2D} object
     * @param gp    a {@link GamePanel} object
     * @param image a {@link BufferedImage} object
     */
    void setValues(Graphics2D g2, GamePanel gp, BufferedImage image) {
        int addY = 0;
        if (name.equals("Exit")) {
            addY = 25;
        }
        int screenX = worldX - gp.getPlayerWorldx() + gp.getPlayerScreenX();
        int screenY = worldY - gp.getPlayerWorldy() + gp.getPlayerScreenY();

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

            g2.drawImage(image, screenX, screenY + addY, null);
            // If player is around the edge, draw everything
        } else if (gp.getPlayerWorldx() < gp.getPlayerScreenX() ||
                gp.getPlayerWorldy() < gp.getPlayerScreenY() ||
                rightOffsetValue > gp.WorldWidth - gp.getPlayerWorldx() ||
                bottomOffsetValue > gp.WorldHeight - gp.getPlayerWorldy()) {
            g2.drawImage(image, screenX, screenY + addY, null);
        }

    }

    /**
     * <p>Getter for the field <code>worldX</code>.</p>
     *
     * @return an int
     */
    public int getWorldX() {
        return worldX;
    }

    /**
     * <p>Getter for the field <code>worldY</code>.</p>
     *
     * @return an int
     */
    public int getWorldY() {
        return worldY;
    }

    /**
     * <p>Setter for the field <code>worldX</code>.</p>
     *
     * @param worldX an int
     */
    public void setWorldX(int worldX) {
        this.worldX = worldX;
    }

    /**
     * <p>Setter for the field <code>worldY</code>.</p>
     *
     * @param worldY an int
     */
    public void setWorldY(int worldY) {
        this.worldY = worldY;
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
