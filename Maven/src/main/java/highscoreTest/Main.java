package highscoreTest;

import game.WinFrame;

import javax.swing.*;
import java.security.SecureRandom;

/**
 * <p>Main class.</p>
 *
 * @author Team Hack-You
 * @version 1.0
 */
public class Main {

    /**
     * <p>main.</p>
     *
     * @param args an array of {@link java.lang.String} objects
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(WinFrame::new);

    }


}
