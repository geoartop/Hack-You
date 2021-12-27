package game;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;

/**
 * <p>Παρουσίαση μελών και ρόλων αυτών</p>
 *
 * @author Team Hack-You
 * @version 1.0
 */
public class Credits extends UtilityFrame {

    /**
     * <p>Constructor for Credits.</p>
     *
     * @param menu a {@link game.Menu} object
     */
    public Credits(Menu menu) {
        super("Credits", 800, 600);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                menu.setCreditsStatus(true);
                frame.dispose();
            }
        });

        try {
            super.load("src/main/resources/sheet-credits.txt", textArea);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        textArea.setCaretPosition(0);
        frame.getContentPane().add(scrollPane);
        frame.add(backgroundLabel);
    }

}
