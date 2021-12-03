package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Κλάση για περιγραφή οδηγιών
 *
 * @author Team Hack-You
 */

public class Guide implements ActionListener {

    JFrame frame;
    JLabel backgroundLabel = new JLabel();

    public Guide() {
        frame = new JFrame();
        // Εξατομίκευση παραθύρου
        FrameSetter.setFrame(frame, "Guide", 600, 600);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                Menu.how2play.setEnabled(true);
                Options.showGuide.setEnabled(true);
                frame.dispose();
            }
        });
        //Set Scaled Background
        FrameSetter.scaleBackground(backgroundLabel, 600, 600);
        frame.add(backgroundLabel);

        //-------test changes end------//
    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }
}