package game;
import javax.swing.*;

/**
 * Σημείο Εκκίνησης παιχνιδιού
 *
 * @author Team Hack-You
 */
public class Main {

    protected static final ImageIcon icon = new ImageIcon("src/main/resources/maze-icon.png");
    protected static final ImageIcon background = new ImageIcon("src/main/resources/background-alt4 - Copy.jpg");

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Username::new);
    }

}