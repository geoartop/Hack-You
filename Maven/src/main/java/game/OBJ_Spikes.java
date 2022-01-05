package game;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 * <p>Αντικείμενο spikes στο παιχνίδι
 * το οποίο απενεργοποιείται με το trigger μιας ερώτησης</p>
 *
 * @author Team Hack-You
 * @version 1.0
 * @see SuperObject
 */
public class OBJ_Spikes extends SuperObject {

    private int spriteNum = 2;
    //Η θέση της συσχετιζόμενης ερώτησης στον πίνακα obj του gamepanel
    private final int question_index;

    /**
     * <p>Constructor for OBJ_Spikes.</p>
     *
     * @param question_index a int
     */
    public OBJ_Spikes(int question_index) {
        super();
        name = "Spikes";
        this.question_index = question_index;
    }

    /**
     * {@inheritDoc}
     * <p>No sound effect for spikes</p>
     */
    @Override
    public void playSE() {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void draw(Graphics2D g2, GamePanel gp) {
        BufferedImage image = Entity.spikes[spriteNum - 1];
        collision = spriteNum == 2;
        super.setValues(g2, gp, image);
        //Απενεργοποίηση του spike μετά το trigger της ερώτησης
        if (gp.obj.get(question_index) == null) {
            spriteNum = 1;
        }
    }

}
