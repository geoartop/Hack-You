package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Κλάση για παρουσίαση μελών και ρόλων αυτών
 *
 * @author Team Hack-You
 */

/*
 * Ιδέα να φορτώνουν τα credits όπως στις ταινίες
 */
public class Credits {

    JFrame frame;
    JLabel backgroundLabel = new JLabel();

    public Credits() {
        frame = new JFrame();
        // Εξατομίκευση παραθύρου
        FrameSetter.setFrame(frame, "Credits", 600, 600);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                Menu.credits.setEnabled(true);
                frame.dispose();
            }
        });
        //-------test changes------//

        //Set Scaled Background
        FrameSetter.scaleBackground(backgroundLabel, 600, 600);
        frame.add(backgroundLabel);


        //-------test changes end------//
    }
}