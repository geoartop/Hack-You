package game;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Κλάση για παρουσίαση μελών και ρόλων αυτών
 *
 * @author Team Hack-You
 */
public class Credits {

    JFrame frame;
    JLabel backgroundLabel = new JLabel();
    Menu menu;

    public Credits(Menu menu) {
        this.menu = menu;
        frame = new JFrame();
        // Εξατομίκευση παραθύρου
        FrameSetter.setFrame(frame, "Credits", 600, 600);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                menu.credits.setEnabled(true);
                frame.dispose();
            }
        });

        //Set Scaled Background
        FrameSetter.scaleBackground(backgroundLabel, 600, 600);
        frame.add(backgroundLabel);

    }
}