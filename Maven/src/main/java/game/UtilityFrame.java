package game;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * SuperClass για τις κλάσεις Credits, Description, Guide, HighScoreFrame
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

    public void load(String pathname,JTextArea textArea) throws FileNotFoundException {
        Scanner q = new Scanner(new File(pathname));
        while (q.hasNextLine())
            textArea.append(q.nextLine() + " ");
    }

    public boolean getIsOpen() {
        return isOpen;
    }

    public void closeFrame() {
        frame.dispose();
    }
}
