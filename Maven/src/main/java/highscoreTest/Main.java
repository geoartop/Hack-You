package highscoreTest;

import game.WinFrame;

import javax.swing.*;
import java.security.SecureRandom;

/**
 * <p>Main class.</p>
 *
 * @author Team Hack-You
 * @version $Id: $Id
 */
public class Main {

    /**
     * <p>main.</p>
     *
     * @param args an array of {@link java.lang.String} objects
     */
    public static void main(String[] args) {
        //for ( PlayerInfo playerInfo : HighScore.playerInfo)
        //    System.out.printf("%s : %d%n",playerInfo.getName(),playerInfo.getScore());
        //new HighScore("Athanasia",33);
        //new HighScore("Athanasia",33);
        SwingUtilities.invokeLater(WinFrame::new);

    }


}
