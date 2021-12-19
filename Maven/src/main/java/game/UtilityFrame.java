package game;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * SuperClass για τις κλάσεις Credits, Description, Guide, HighScoreFrame
 * <p>
 * Χρησιμότητα : εύκολο μαζικό κλείσιμο frame + ευκολία υλοποίησης
 */
public abstract class UtilityFrame {

    private final boolean isOpen;
    protected JFrame frame;
    protected JLabel backgroundLabel = new JLabel();
    protected JTextArea textArea = new JTextArea();
    protected JScrollPane scrollPane;

    public UtilityFrame(String title, int width, int height) {
        isOpen = true;
        frame = new JFrame();
        // Εξατομίκευση παραθύρου
        FrameSetter.setFrame(frame, title, width, height);
        //Set Scaled Background
        FrameSetter.scaleBackground(backgroundLabel, width, height);

        textArea.setBounds(100, 0, 600, 800);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setOpaque(false);
        textArea.setForeground(Color.black);
        textArea.setFont(new Font("Calibri", Font.BOLD, 20));
        textArea.setEditable(false);

        scrollPane = createScrollPane(textArea, 600, 500);

    }

    /**
     * Φόρτωση αρχείου κειμένου
     * @param pathname το path του αρχείου
     * @param textArea το textArea στο οποίο θα φορτωθεί το κείμενο
     */
    protected void load(String pathname, JTextArea textArea) throws FileNotFoundException {
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
    private JScrollPane createScrollPane(JTextArea textArea, int width, int height) {
        //Για να εμφανίζεται το περιεχόμενο του textArea από την αρχή
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
