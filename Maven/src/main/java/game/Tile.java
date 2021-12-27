package game;

import java.awt.image.BufferedImage;

/**
 * <p>Κουτί ("μπλοκ") του χάρτη του παιχνιδιού</p>
 *
 * @author Team Hack-You
 * @version 1.0
 */
public final class Tile {

    private final BufferedImage image;
    private final boolean collision;

    /**
     * <p>Constructor for Tile.</p>
     *
     * @param image     a {@link java.awt.image.BufferedImage} object
     * @param collision a boolean
     */
    public Tile(BufferedImage image, boolean collision) {
        this.image = image;
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

}
