package game;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;
import javax.imageio.ImageIO;

/**
 * <p>Αντικείμενο ερώτησης στο παιχνίδι</p>
 *
 * @author Team Hack-You
 * @version 1.0
 * @see SuperObject
 */
public class OBJ_Question extends SuperObject {

    private static BufferedImage image;
    /**
     * <p>Constructor for OBJ_Question.</p>
     */
    public OBJ_Question() {
        super("Question", true);
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

    /**
     * <p>setup.</p>
     */
    static void setup() {
        image = SuperObject.setup("/icons/qmark.png");
    }
}
