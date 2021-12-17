package game;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.plaf.ScrollBarUI;
import javax.swing.plaf.basic.BasicScrollBarUI;
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

    public void load(String pathname, JTextArea textArea) throws FileNotFoundException {
        Scanner q = new Scanner(new File(pathname));
        while (q.hasNextLine())
            textArea.append(q.nextLine() + "\n");
    }

    public boolean getIsOpen() {
        return isOpen;
    }

    protected JScrollPane createScrollPane(JTextArea textArea, int width, int height) {
        //Για να εμφανίζεται το περιεχόμενο του textArea από την αρχή
        textArea.setCaretPosition(0);
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setOpaque(false);
        scrollPane.createVerticalScrollBar();
        scrollPane.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
            @Override
            protected void configureScrollBarColors() {
                this.thumbColor = new Color(93, 91, 91);
            }
        });
        scrollPane.getVerticalScrollBar().setOpaque(false);
        //scrollPane.getVerticalScrollBar().setUI();
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
