package game;

import java.awt.*;

/**
 * Αντικείμενο Εξόδου στο παιχνίδι
 *
 * @author Team Hack-You
 */
public class OBJ_Exit extends SuperObject {

    /**
     * <p>Constructor for OBJ_Exit.</p>
     */
    public OBJ_Exit() {
        super("/icons/exit.png", 48);
        name = "Exit";
        collision = false;
    }

    /** {@inheritDoc} */
    @Override
    public void playSE() {
        //se.setFile();
        //se.play();
    }

    /** {@inheritDoc} */
    @Override
    public void draw(Graphics2D g2, GamePanel gp){
        setValues(g2, gp, image);
    }

}
