package game;

import java.awt.*;

/**
 * Αντικείμενο Εξόδου στο παιχνίδι
 */
public class OBJ_Exit extends SuperObject {

    public OBJ_Exit() {
        super("/icons/exit.png", 48);
        name = "Exit";
        collision = false;
    }

    @Override
    public void draw(Graphics2D g2, GamePanel gp){
        setValues(g2, gp, image);
    }

}
