package game;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * <p>Παρουσίαση μελών και ρόλων αυτών.</p>
 *
 * @author Team Hack-You
 * @version 1.0
 * @see UtilityFrame
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

        super.load("src/main/resources/sheet-credits.txt", textPane);

        textPane.setCaretPosition(0);
        frame.getContentPane().add(scrollPane);
        frame.add(backgroundLabel);
    }

}
