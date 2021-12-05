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
    Menu menu;
    Options options;

    /**
     * Κατασκευαστής που καλείται όταν το guide ανοίγει από το παράθυρο options
     * @param options : Το παράθυρο options από το οποίο κλήθηκε ο guide
     */
    public Guide(Options options) {
        this.options = options;
        buildGuide();
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                options.showGuide.setEnabled(true);
                Options.guideOpen = false;
                frame.dispose();
            }
        });

    }

    /**
     * Κατασκευαστής που καλείται όταν το guide ανοίγει από το παράθυρο menu
     * @param menu : Το παράθυρο menu από το οποίο κλήθηκε ο guide
     */
    public Guide(Menu menu) {
        this.menu = menu;
        buildGuide();
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                menu.how2play.setEnabled(true);
                frame.dispose();
            }
        });
    }

    private void buildGuide(){
        frame = new JFrame();
        // Εξατομίκευση παραθύρου
        FrameSetter.setFrame(frame, "Guide", 600, 600);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        //Set Scaled Background
        FrameSetter.scaleBackground(backgroundLabel, 600, 600);
        frame.add(backgroundLabel);
    }


    public void closeFrame() {
        frame.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}