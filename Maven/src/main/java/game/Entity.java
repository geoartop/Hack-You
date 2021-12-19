package game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Entity {

    protected int worldx, worldy;
    protected int speed;

    //Animations for player
    protected final static BufferedImage[] up = new BufferedImage[9];
    protected final static BufferedImage[] down = new BufferedImage[9];
    protected final static BufferedImage[] right = new BufferedImage[9];
    protected final static BufferedImage[] left = new BufferedImage[9];
    protected final static BufferedImage[] death = new BufferedImage[7];
    //Animations for coin
    final static BufferedImage[] coin = new BufferedImage[9];

    protected String direction;

    //Μεταβλητές παίκτη
    protected int spriteCounter = 0;
    protected int spriteNum = 1;
    protected int solidAreaDefaultX, solidAreaDefaultY;
    protected Rectangle solidArea;
    protected boolean collisionOn = false;

    /**
     * Προετοιμασία των animation
     */
    public void getImage() {
        try {
            setMovement(up, "thiseaswalkingup");
            setMovement(down, "thiseaswalkingdown");
            setMovement(right, "thiseaswalkingright");
            setMovement(left, "thiseaswalkingleft");
            for (int i = 0; i < coin.length; i++) {
                BufferedImage image = ImageIO.read(getClass().getResourceAsStream(String.format("/goldCoin/goldCoin%d.png", i + 1)));
                coin[i] = FrameSetter.scaleImage(image, 38, 38);
            }
            for (int i = 0; i < death.length - 1; i++) {
                BufferedImage image = ImageIO.read(getClass().getResourceAsStream(String.format("/deadthiseas/dead%d.png", i + 1)));
                death[i] = FrameSetter.scaleImage(image, 48, 48);
            }
            death[death.length - 1] = death[death.length - 2];
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Διάβασμα αρχείων για τη φόρτωση των animation
     *
     * @param images : ο πίνακας εικόνων κινήσεων
     * @param move   : καθορίζει την κατηγορία κίνησης
     */
    private void setMovement(BufferedImage[] images, String move) throws IOException {
        for (int i = 0; i < images.length; i++) {
            BufferedImage image = ImageIO.read(getClass().getResourceAsStream(String.format("/thiseas2/%s%d.png", move, i + 1)));
            images[i] = FrameSetter.scaleImage(image, 48, 48);
        }

    }

}
