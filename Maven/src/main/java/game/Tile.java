package game;

import java.awt.image.BufferedImage;

/**
 * Κουτί ("μπλοκ") του χάρτη του παιχνιδιού
 *
 * @author Team Hack-You
 * @version 1.0
 */
public final class Tile {

    private BufferedImage image;
    private boolean collision = false;

    /**
     * <p>Setter for the field <code>collision</code>.</p>
     *
     * @param collision a boolean
     */
    public void setCollision(boolean collision) {
        this.collision = collision;
    }

    /**
     * <p>Getter for the field <code>collision</code>.</p>
     *
     * @return a boolean
     */
    public boolean getCollision() {
        return collision;
    }

    /**
     * <p>Getter for the field <code>image</code>.</p>
     *
     * @return a {@link java.awt.image.BufferedImage} object
     */
    public BufferedImage getImage() {
        return image;
    }

    /**
     * <p>Setter for the field <code>image</code>.</p>
     *
     * @param image a {@link java.awt.image.BufferedImage} object
     */
    public void setImage(BufferedImage image) {
        this.image = image;
    }
}
