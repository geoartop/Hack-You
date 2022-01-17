package game;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;
import javax.imageio.ImageIO;

/**
 * <p>Δημιουργία αντικειμένων στο παιχνίδι.</p>
 *
 * @author Team Hack-You
 * @version 1.0
 */
public abstract class SuperObject {

    private final String name;
    protected boolean collision;
    private int worldX, worldY;
    protected final Rectangle solidArea
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
     * @param name      a {@link java.lang.String} object
     * @param collision a boolean
     */
    public SuperObject(String name, boolean collision) {
        this.name = name;
        this.collision = collision;
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

        if (gp.WorldWidth - gp.getPlayerWorldx() < rightOffsetValue) {
            screenX = GamePanel.screenWidth - (gp.WorldWidth - worldX);
        }

        int bottomOffsetValue = GamePanel.screenHeight - gp.getPlayerScreenY();

        if (gp.WorldHeight - gp.getPlayerWorldy() < bottomOffsetValue) {
            screenY = GamePanel.screenHeight - (gp.WorldHeight - worldY);
        }

        if (gp.getPlayerWorldx() - gp.getPlayerScreenX() < worldX + GamePanel.tileSize &&
                gp.getPlayerWorldx() + gp.getPlayerScreenX() > worldX - GamePanel.tileSize &&
                gp.getPlayerWorldy() - gp.getPlayerScreenY() < worldY + GamePanel.tileSize &&
                gp.getPlayerWorldy() + gp.getPlayerScreenY() > worldY - GamePanel.tileSize) {

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


    /**
     * <p>setup images for game objects.</p>
     *
     * @param path a {@link java.lang.String} object
     * @return a {@link BufferedImage} object
     */
    static BufferedImage setup(String path) {
        BufferedImage image = null;
        try {
            image = ImageIO.read(Objects.requireNonNull(SuperObject.class.getResourceAsStream(path)));
            image = FrameSetter.scaleImage(image, 48, 48);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }

    /**
     * <p>Getter for the field <code>name</code>.</p>
     *
     * @return a {@link java.lang.String} object
     */
    public String getName() {
        return name;
    }

}
