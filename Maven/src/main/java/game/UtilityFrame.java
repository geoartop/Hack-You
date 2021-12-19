package game;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * SuperClass για τις κλάσεις Credits, Description, Guide, HighScoreFrame
 * <p>
 * Χρησιμότητα : εύκολο μαζικό κλείσιμο frame + ευκολία υλοποίησης
 */
public abstract class UtilityFrame {

    private boolean isOpen;
    protected JFrame frame;
    public JLabel backgroundLabel = new JLabel();

    public UtilityFrame(String title, int width, int height) {
        isOpen = true;
        frame = new JFrame();
        // Εξατομίκευση παραθύρου
        FrameSetter.setFrame(frame, title, width, height);
        //Set Scaled Background
        FrameSetter.scaleBackground(backgroundLabel, width, height);
    }

    /**
     * Φόρτωση αρχείου κειμένου
     * @param pathname το path του αρχείου
     * @param textArea το textArea στο οποίο θα φορτωθεί το κείμενο
     */
    public void load(String pathname, JTextArea textArea) throws FileNotFoundException {
        Scanner q = new Scanner(new File(pathname),"UTF-8");
        while (q.hasNextLine())
            textArea.append(q.nextLine() + "\n");
    }

    public boolean getIsOpen() {
        return isOpen;
    }

    /**
     * Δημιουργία scrollable textArea
     * @param textArea το text που θέλουμε να εμφανιστεί
     * @param width πλάτος textArea που επιθυμούμε
     * @param height ύψος textArea που επιθυμούμε
     * @return scrollPane
     */
    protected JScrollPane createScrollPane(JTextArea textArea, int width, int height) {
        //Για να εμφανίζεται το περιεχόμενο του textArea από την αρχή
        textArea.setCaretPosition(0);
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setOpaque(false);
        scrollPane.createVerticalScrollBar();
        scrollPane.getVerticalScrollBar().setOpaque(false);
        scrollPane.setBounds(100, 25, width, height);
        scrollPane.setViewportView(textArea);
        scrollPane.setBorder(null);
        //Για να γίνει διάφανο το πλαίσιο εμφάνισης κειμένου
        scrollPane.getViewport().setOpaque(false);
        return scrollPane;
    }

    public void closeFrame() {
        frame.dispose();
    }
}
