package game;

import java.awt.Color;
import java.awt.Font;
import java.io.InputStream;
import java.util.Objects;
import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.StyledDocument;

/**
 * <p> SuperClass για τις κλάσεις Credits, Description, Guide, HighScoreFrame </p>
 * <p> Χρησιμότητα : </p>
 * <p>εύκολο μαζικό κλείσιμο frame</p>
 * <p>παροχή ορισμένου πρότυπου frame και λειτουργιών</p>
 *
 * @author Team Hack-You
 * @version 1.0
 */
public abstract class UtilityFrame {

    private final boolean isOpen;
    protected final JFrame frame;
    protected final JLabel backgroundLabel = new JLabel();
    protected final JTextPane textPane = new JTextPane();
    protected final JScrollPane scrollPane;

    /**
     * Διαστάσεις scrollPane
     */
    private static final int WIDTH = 600;
    private static final int HEIGHT = 500;

    /**
     * <p>Constructor for UtilityFrame.</p>
     *
     * @param title  a {@link java.lang.String} object
     * @param width  an int
     * @param height an int
     */
    public UtilityFrame(String title, int width, int height) {
        isOpen = true;
        frame = new JFrame();
        // Εξατομίκευση παραθύρου
        FrameSetter.setFrame(frame, title, width, height);
        //Set Scaled Background
        FrameSetter.scaleBackground(backgroundLabel, width, height);

        textPane.setOpaque(false);
        textPane.setEditable(false);
        textPane.setFont(new Font("Calibri", Font.BOLD, 20));
        textPane.setForeground(Color.black);

        scrollPane = createScrollPane(textPane);

    }

    /**
     * <p>Φόρτωση αρχείου κειμένου.</p>
     *
     * @param pathname το path του αρχείου
     * @param textPane {@link javax.swing.JTextPane} το textPane στο οποίο θα φορτωθεί το κείμενο
     */
    protected void load(String pathname, JTextPane textPane) {
        InputStream is = UtilityFrame.class.getResourceAsStream(pathname);
        StyledDocument doc = textPane.getStyledDocument();
        try {
            Scanner q = new Scanner(Objects.requireNonNull(is), "UTF-8");
            while (q.hasNextLine()) {
                doc.insertString(doc.getLength(), q.nextLine() + "\n", null);
            }
            q.close();
        } catch (BadLocationException e) {
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
     * <p>Δημιουργία scrollable textPane.</p>
     *
     * @param textPane {@link JTextPane} το textPane που θέλουμε να εμφανιστεί
     * @return scrollPane a {@link JScrollPane} object
     */
    private JScrollPane createScrollPane(JTextPane textPane) {
        //Για να εμφανίζεται το περιεχόμενο του textArea από την αρχή
        JScrollPane scrollPane = new JScrollPane(textPane);
        scrollPane.setOpaque(false);
        scrollPane.createVerticalScrollBar();
        scrollPane.getVerticalScrollBar().setOpaque(false);
        scrollPane.setBounds(100, 25, WIDTH, HEIGHT);
        scrollPane.setViewportView(textPane);
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
