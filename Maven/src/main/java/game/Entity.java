package game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Entity {

    public int x, y;
    public int speed;

    //Animations
    public static BufferedImage[] up = new BufferedImage[9];
    public static BufferedImage[] down = new BufferedImage[9];
    public static BufferedImage[] right = new BufferedImage[9];
    public static BufferedImage[] left = new BufferedImage[9];
    public static BufferedImage[] death = new BufferedImage[7];

    public String direction;

    //Μεταβλητές παίκτη
    public int spriteCounter = 0;
    public int spriteNum = 1;
    public int solidAreaDefaultX, solidAreaDefaultY;
    public Rectangle solidArea;
    public boolean collisionOn = false;

    /**
     * Προετοιμασία των animation
     */
    public void getImage() {
        try {
            setMovement(up, "thiseaswalkingup");
            setMovement(down, "thiseaswalkingdown");
            setMovement(right, "thiseaswalkingright");
            setMovement(left, "thiseaswalkingleft");
            for (int i = 0; i < death.length - 1; i++)
                death[i] = ImageIO.read(getClass().getResourceAsStream(String.format("/deadthiseas/dead%d.png", i + 1)));
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
     * @throws IOException
     */
    private void setMovement(BufferedImage[] images, String move) throws IOException {
        for (int i = 0; i < images.length; i++)
            images[i] = ImageIO.read(getClass().getResourceAsStream(String.format("/thiseas2/%s%d.png", move, i + 1)));

    }

}
