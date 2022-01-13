package game;

import highscoreTest.HighScore;
import javax.swing.SwingUtilities;
import java.awt.Color;
import java.awt.Font;

/**
 * <p>Σημείο Εκκίνησης παιχνιδιού</p>
 *
 * @author Team Hack-You
 * @version 1.0
 */
public class Main {

    /**
     * Game mainly used color <code>mainColor</code>
     */
    public static final Color mainColor = new Color(134, 1, 1, 196);
    /**
     * Game (most common) font <code>mainFont</code>
     */
    public static final Font mainFont = new Font("Calibri", Font.ITALIC, 20);

    /**
     * <p>main.</p>
     *
     * @param args an array of {@link java.lang.String} objects
     */
    public static void main(String[] args) {
        setupResources();
        SwingUtilities.invokeLater(Username::new);
    }

    /**
     * <p>setupResources.</p>
     */
    private static void setupResources() {
        FrameSetter.setup();
        ButtonSetter.setupButtons();
        Menu.setup();
        Entity.getImages();
        DeathFrame.setup();
        WinFrame.setup();
        Guide.setupImages();
        Levels.setup();
        HighScore.setup();
    }

    /**
     * <p>exit game.</p>
     */
    public static void exit() {
        System.exit(0);
    }

}
