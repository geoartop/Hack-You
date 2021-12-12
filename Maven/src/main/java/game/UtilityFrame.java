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
public abstract class UtilityFrame {

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

    protected JScrollPane createScrollPane(JTextArea textArea,int width,int height){
        textArea.setCaretPosition(0);
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setOpaque(false);
        scrollPane.createVerticalScrollBar();
        scrollPane.setBounds(100, 0, width, height);
        scrollPane.setViewportView(textArea);
        //Για να γίνει διάφανο το πλαίσιο εμφάνισης κειμένου
        scrollPane.getViewport().setOpaque(false);
        return scrollPane;
    }

    public void closeFrame() {
        frame.dispose();
    }
}
