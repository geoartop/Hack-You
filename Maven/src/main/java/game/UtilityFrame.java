package game;

import javax.swing.*;

/**
 * SuperClass για τις κλάσεις Credits, Description, Guide
 *
 * Χρησιμότητα : εύκολο μαζικό κλείσιμο frame + ευκολία υλοποίησης
 */
public class UtilityFrame {

    private boolean isOpen;
    protected JFrame frame;
    JLabel backgroundLabel = new JLabel();

    public UtilityFrame() {
        isOpen = true;
        frame = new JFrame();
        // Εξατομίκευση παραθύρου
        FrameSetter.setFrame(frame, "Guide", 800, 800);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        //Set Scaled Background
        FrameSetter.scaleBackground(backgroundLabel, 800, 800);
    }

    public boolean getIsOpen() {
        return isOpen;
    }

    public void closeFrame() {
        frame.dispose();
    }
}
