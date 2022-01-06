package game;

import javax.swing.ImageIcon;
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
     * WindowIcon <code>icon</code>
     */
    public static final ImageIcon icon = new ImageIcon("src/main/resources/icons/maze-icon.png");
    /**
     * Background <code>background</code>
     */
    public static final ImageIcon background = new ImageIcon("src/main/resources/background/background-alt4 - Copy.jpg");
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
        SwingUtilities.invokeLater(Username::new);
    }

}
