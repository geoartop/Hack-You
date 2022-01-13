package game;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;
import javax.imageio.ImageIO;

/**
 * <p>Αντικείμενο Εξόδου στο παιχνίδι</p>
 *
 * @author Team Hack-You
 * @version 1.0
 * @see SuperObject
 */
public class OBJ_Exit extends SuperObject {

    private static BufferedImage image;

    /**
     * <p>Constructor for OBJ_Exit.</p>
     */
    public OBJ_Exit() {
        super("Exit", false);
        super.solidArea.height = 96;
    }

    /**
     * {@inheritDoc}
     */
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

    /**
     * <p>setup.</p>
     */
    static void setup() {
        image = SuperObject.setup("/icons/exit.png");
    }

}
