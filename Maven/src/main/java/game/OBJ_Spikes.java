package game;

import java.awt.*;
import java.awt.image.BufferedImage;

public class OBJ_Spikes extends SuperObject {
    //private int spriteCounter = 0;
    private int spriteNum = 2;
    private final int question_index;

    public OBJ_Spikes(int question_index) {
        super();
        name = "Spikes";
        this.question_index = question_index;
    }

    @Override
    public void draw(Graphics2D g2, GamePanel gp) {
        BufferedImage image = Entity.spikes[spriteNum - 1];
        collision = spriteNum == 2;
        super.setValues(g2, gp, image);
        OBJ_Question question = (OBJ_Question) gp.obj.get(question_index);
        if (question == null)
            spriteNum = 1;
    }

    /*public void drawSpikes(Graphics2D g2, GamePanel gp) {
        BufferedImage image = Entity.spikes[spriteNum - 1];
        collision = spriteNum >= 3;
        super.setValues(g2, gp, image);
        spriteCounter++;
        if (spriteCounter > 60) {
            if (spriteNum < 4) {
                spriteNum++;
            } else {
                spriteNum = 1;
            }
            spriteCounter = 0;
        }
    }*/
}
