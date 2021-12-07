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
    public JLabel backgroundLabel = new JLabel();

    public UtilityFrame(String title,int width,int height) {
        isOpen = true;
        frame = new JFrame();
        // Εξατομίκευση παραθύρου
        FrameSetter.setFrame(frame, title, width, height);
        //Set Scaled Background
        FrameSetter.scaleBackground(backgroundLabel, width, height);
    }

    public boolean getIsOpen() {
        return isOpen;
    }

    public void closeFrame() {
        frame.dispose();
    }
}
