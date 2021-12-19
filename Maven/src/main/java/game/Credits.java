package game;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Παρουσίαση μελών και ρόλων αυτών
 *
 * @author Team Hack-You
 */
public class Credits extends UtilityFrame {

    public Credits(Menu menu) {
        super("Credits", 800, 800);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                menu.credits.setEnabled(true);
                frame.dispose();
            }
        });
        frame.add(backgroundLabel);
    }

}