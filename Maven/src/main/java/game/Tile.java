package game;

import java.awt.image.BufferedImage;

/**
 * Κουτί ("μπλοκ") του χάρτη του παιχνιδιού
 */
public final class Tile {

    private BufferedImage image;
    private boolean collision = false;

    public void setCollision(boolean collision) {
        this.collision = collision;
    }

    public boolean getCollision() {
        return collision;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }
}
