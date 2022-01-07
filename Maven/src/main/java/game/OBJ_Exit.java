package game;

import java.awt.Graphics2D;
import java.awt.Rectangle;

/**
 * <p>Αντικείμενο Εξόδου στο παιχνίδι</p>
 *
 * @author Team Hack-You
 * @version 1.0
 * @see SuperObject
 */
public class OBJ_Exit extends SuperObject {

    /**
     * <p>Constructor for OBJ_Exit.</p>
     */
    public OBJ_Exit() {
        super("/icons/exit.png", 48);
        super.solidArea = new Rectangle(0, 0, 48, 96);
        name = "Exit";
        collision = false;
    }

    /** {@inheritDoc} */
    @Override
    public void playSE() {
        se.setFile(3);
        se.play();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void draw(Graphics2D g2, GamePanel gp) {
        setValues(g2, gp, image);
    }

}
