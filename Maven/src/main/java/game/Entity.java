package game;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

/**
 * <p>Entity class. <br>
 * Φορτώνει τα animation λων των αντικειμένων του παιχνιδιού.</p>
 *
 * @author Team Hack-You
 * @version 1.0
 */
public class Entity {

    //Animations for player
    /**
     * <code>up</code> movement animation
     */
    final static BufferedImage[] up = new BufferedImage[9];
    /**
     * <code>down</code> movement animation
     */
    final static BufferedImage[] down = new BufferedImage[9];
    /**
     * <code>right</code> movement animation
     */
    final static BufferedImage[] right = new BufferedImage[9];
    /**
     * <code>left</code> movement animation
     */
    final static BufferedImage[] left = new BufferedImage[9];
    /**
     * <code>death</code> movement animation
     */
    final static BufferedImage[] death = new BufferedImage[7];

    /**
     * <code>OBJ_Coin</code> movement animation
     */
    final static BufferedImage[] coin = new BufferedImage[9];
    /**
     * <code>OBJ_Spikes</code> animation
     */
    final static BufferedImage[] spikes = OBJ_Spikes.setup();


    /**
     * <p>Προετοιμασία των animations</p>
     */
    static void getImages() {
        try {
            setMovement(up, "/thiseas2/thiseaswalkingup");
            setMovement(down, "/thiseas2/thiseaswalkingdown");
            setMovement(right, "/thiseas2/thiseaswalkingright");
            setMovement(left, "/thiseas2/thiseaswalkingleft");
            setMovement(death, "/deadthiseas/dead");

            setMovement(coin, "/goldCoin/goldCoin");
            OBJ_Question.setup();
            OBJ_Exit.setup();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * <p>Διάβασμα αρχείων για τη φόρτωση των animation</p>
     *
     * @param images ο πίνακας {@link java.awt.image.BufferedImage} εικόνων κινήσεων
     * @param path   καθορίζει το επιθυμητό path
     * @throws IOException if any
     */
    private static void setMovement(BufferedImage[] images, String path) throws IOException {
        for (int i = 0; i < images.length; i++) {
            BufferedImage image = ImageIO.read(Objects.requireNonNull
                    (Entity.class.getResourceAsStream(path + (i + 1) + ".png")));
            images[i] = FrameSetter.scaleImage(image, 48, 48);
            //For death animation
            if (i == 5 && images.length == 7) {
                images[images.length - 1] = images[images.length - 2];
                break;
            }

        }

    }

}
