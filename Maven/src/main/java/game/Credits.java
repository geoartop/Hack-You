package game;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;

/**
 * Παρουσίαση μελών και ρόλων αυτών
 *
 * @author Team Hack-You
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
                menu.credits.setEnabled(true);
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
