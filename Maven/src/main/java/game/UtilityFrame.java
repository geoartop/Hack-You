package game;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.Color;
import java.awt.Font;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * <p> SuperClass για τις κλάσεις Credits, Description, Guide, HighScoreFrame </p>
 * <p> Χρησιμότητα : εύκολο μαζικό κλείσιμο frame + παροχή ορισμένου πρότυπου frame και λειτουργιών </p>
 *
 * @author Team Hack-You
 * @version 1.0
 */
public abstract class UtilityFrame {

    private final boolean isOpen;
    protected final JFrame frame;
    protected final JLabel backgroundLabel = new JLabel();
    protected final JTextArea textArea = new JTextArea();
    protected final JScrollPane scrollPane;

    /**
     * <p>Constructor for UtilityFrame.</p>
     *
     * @param title  a {@link java.lang.String} object
     * @param width  a int
     * @param height a int
     */
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
     * <p>Φόρτωση αρχείου κειμένου</p>
     *
     * @param pathname το path του αρχείου
     * @param textArea το textArea στο οποίο θα φορτωθεί το κείμενο
     */
    protected void load(String pathname, JTextArea textArea) {
        try {
            Scanner q = new Scanner(new File(pathname), "UTF-8");
            while (q.hasNextLine()) {
                textArea.append(q.nextLine() + "\n");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    /**
     * <p>Getter for the field <code>isOpen</code>.</p>
     *
     * @return a boolean
     */
    public boolean getIsOpen() {
        return isOpen;
    }

    /**
     * <p>Δημιουργία scrollable textArea</p>
     *
     * @param textArea το text που θέλουμε να εμφανιστεί
     * @param width    πλάτος textArea που επιθυμούμε
     * @param height   ύψος textArea που επιθυμούμε
     * @return scrollPane a {@link JScrollPane} object
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

    /**
     * <p>closeFrame.</p>
     */
    public void closeFrame() {
        frame.dispose();
    }
}
