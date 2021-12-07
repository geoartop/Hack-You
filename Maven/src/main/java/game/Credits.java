package game;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Κλάση για παρουσίαση μελών και ρόλων αυτών
 *
 * @author Team Hack-You
 */
public class Credits extends UtilityFrame{

    Menu menu;

    public Credits(Menu menu) {
        super("Credits",800,800);
        this.menu = menu;
        super.frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                menu.credits.setEnabled(true);
                Credits.super.frame.dispose();
            }
        });
        frame.add(backgroundLabel);
    }

}