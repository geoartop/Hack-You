package game;

import java.awt.Graphics2D;

/**
 * <p>Αντικείμενο ερώτησης στο παιχνίδι</p>
 *
 * @author Team Hack-You
 * @version 1.0
 * @see SuperObject
 */
public class OBJ_Question extends SuperObject {

    /**
     * <p>Constructor for OBJ_Question.</p>
     */
    public OBJ_Question() {
        super("/icons/qmark.png", 48);
        name = "Question";
        collision = true;
    }

    /** {@inheritDoc} */
    @Override
    public void playSE() {
        se.setFile(4);
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
