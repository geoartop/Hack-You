package game;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Αντικείμενο coin στο παιχνίδι
 *
 * @author Team Hack-You
 * @version 1.0
 */
public class OBJ_Coin extends SuperObject {

    private int spriteCounter = 0;
    private int spriteNum = 1;

    /**
     * <p>Constructor for OBJ_Coin.</p>
     */
    public OBJ_Coin() {
        super();
        name = "Coin";
        collision = false;
    }

    /**
     * <p>playSE.</p>
     */
    public void playSE() {
        se.setFile(2);
        se.play();
    }

    /** {@inheritDoc} */
    @Override
    public void draw(Graphics2D g2, GamePanel gp) {
        BufferedImage image = Entity.coin[spriteNum - 1];
        super.setValues(g2, gp, image);
        spriteCounter++;
        if (spriteCounter > 7) {
            if (spriteNum < 9) {
                spriteNum++;
            } else {
                spriteNum = 1;
            }
            spriteCounter = 0;
        }


    }

}
