package game;

import javax.imageio.ImageIO;
import java.io.IOException;

/**
 * Αντικείμενο ερώτησης στο παιχνίδι
 */
public class OBJ_Question extends SuperObject {

    public OBJ_Question() {
        name = "Question";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/icons/qmark.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        collision = true;
    }
}
