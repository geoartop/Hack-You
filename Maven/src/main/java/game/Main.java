package game;

import javax.swing.*;
import java.awt.*;

/**
 * Σημείο Εκκίνησης παιχνιδιού
 *
 * @author Team Hack-You
 * @version 1.0
 */
public class Main {

    static final ImageIcon icon = new ImageIcon("src/main/resources/icons/maze-icon.png");
    static final ImageIcon background = new ImageIcon("src/main/resources/background/background-alt4 - Copy.jpg");
    static final Color mainColor = new Color(134, 1, 1, 196);

    /**
     * <p>main.</p>
     *
     * @param args an array of {@link java.lang.String} objects
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(Username::new);
    }

}
