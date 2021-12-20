package game;

import java.awt.*;

/**
 * Αντικείμενο ερώτησης στο παιχνίδι
 */
public class OBJ_Question extends SuperObject {


    public OBJ_Question() {
        super("/icons/qmark.png", 48);
        name = "Question";
        collision = true;
    }

    @Override
    public void draw(Graphics2D g2,GamePanel gp){
        setValues(g2, gp, image);
    }
}
