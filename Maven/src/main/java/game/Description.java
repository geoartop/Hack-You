package game;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Κλάση που περιγράφει το παιχνίδι και τη "πλοκή"
 *
 * @author Team Hack-You
 */
public class Description {

    JFrame frame;
    JLabel backgroundLabel = new JLabel();

    public Description() {
        frame = new JFrame(); //create frame
        FrameSetter.setFrame(frame, "Game description", 600, 600);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                Menu.description.setEnabled(true);
                frame.dispose();
            }
        });

        //Set Scaled Background
        FrameSetter.scaleBackground(backgroundLabel, 600, 600);
        frame.add(backgroundLabel);
    }
}