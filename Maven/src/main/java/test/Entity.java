package test;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity {

    public int x, y;
    public int speed;

    public BufferedImage[] up = new BufferedImage[9];
    public BufferedImage[] down = new BufferedImage[9];
    public BufferedImage[] right = new BufferedImage[9];
    public BufferedImage[] left = new BufferedImage[9];
    public String direction;

    public int spriteCounter = 0;
    public int spriteNum = 1;

    public Rectangle solidArea;
    public boolean collisionOn = false;
}
