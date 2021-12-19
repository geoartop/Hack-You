package game;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Αντικείμενο coin στο παιχνίδι
 */
public class OBJ_Coin extends SuperObject {

    private final Sound se = new Sound();

    private int spriteCounter = 0;
    private int spriteNum = 1;

    public OBJ_Coin() {
        super();
        name = "Coin";
        collision = false;
        se.setFile(2);
    }

    public void playSE() {
        se.setFile(2);
        se.play();
    }

    public void drawCoin(Graphics2D g2, GamePanel gp) {
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