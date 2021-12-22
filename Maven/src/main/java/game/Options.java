package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Παράθυρο επιλόγων που προκαλεί παύση του παιχνιδιού όταν εμφανίζεται
 *
 * @author Team Hack-You
 * @version 1.0
 */
public final class Options implements ActionListener {

    private final GamePanel gp;
    private final JFrame frame;
    private final JLabel backgroundLabel = new JLabel();
    private final JButton returnBack = new JButton("return");
    final JButton showGuide = new JButton("show Guide");
    private final JButton restart = new JButton("restart");
    private final JButton end = new JButton("exit");
    private static boolean isActive = false;
    private Guide guide;

    /**
     * <p>Constructor for Options.</p>
     *
     * @param gp a {@link game.GamePanel} object
     */
    public Options(GamePanel gp) {
        isActive = true;
        this.gp = gp;
        frame = new JFrame();
        frame.setTitle("Options"); //setTitle of frame
        FrameSetter.setFrame(frame, "Options", 600, 650);
        //Θέτω το κουμπί της εξόδου να κάνει αυτόματα click το return για να μην κολλήσει η ροή του LabyrinthFrame
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                returnBack.doClick();
            }
        });
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        GraphicPane graphicPane = new GraphicPane("Options", 600, 100, Main.mainColor, new Font("Times new Roman", Font.BOLD + Font.ITALIC, 50));
        graphicPane.setBounds(0, 0, 600, 150);
        frame.add(graphicPane);

        ButtonSetter.setButton(returnBack, 225, 200, 150, 50, new Font("Calibri", Font.ITALIC, 20), this);
        ButtonSetter.setButton(showGuide, 225, 300, 150, 50, new Font("Calibri", Font.ITALIC, 20), this);
        ButtonSetter.setButton(restart, 225, 400, 150, 50, new Font("Calibri", Font.ITALIC, 20), this);
        ButtonSetter.setButton(end, 225, 500, 150, 50, new Font("Calibri", Font.ITALIC, 20), this);

        frame.add(returnBack);
        frame.add(showGuide);
        frame.add(restart);
        frame.add(end);

        FrameSetter.scaleBackground(backgroundLabel, 600, 650);
        frame.add(backgroundLabel);
    }

    /**
     * <p>Getter for the field <code>isActive</code>.</p>
     *
     * @return a boolean
     */
    public static boolean getIsActive() {
        return isActive;
    }

    /**
     * <p>check</p>
     * Έλεγχος για τον αν υπάρχει ανοιχτό παράθυρο guide
     * Σε περίπτωση που υπάρχει το παράθυρο αυτό κλείνει
     */
    private void check() {
        if (guide == null)
            return;
        if (guide.getIsOpen())
            guide.closeFrame();
    }

    /** {@inheritDoc} */
    @Override
    public void actionPerformed(ActionEvent e) {
        ButtonSetter.playSE();
        if (e.getSource() == returnBack) {
            check();
            frame.dispose();
        } else if (e.getSource() == showGuide) {
            guide = new Guide(this);
            showGuide.setEnabled(false);
            return;
        } else if (e.getSource() == restart) {
            gp.labyrinthFrame.closeFrame();
            check();
            SwingUtilities.invokeLater(LabyrinthFrame::new);
            frame.dispose();
            Quiz.indexes.clear();
        } else {
            System.exit(1);
        }
        //Για να μην κολλήσει το progressBar
        if (gp.labyrinthFrame.getHasStarted()) {
            gp.gameState = gp.playState;
            gp.labyrinthFrame.updateBar(0);
        }
        // Ενημερώνουμε το gamepanel για το κλείσιμο του παραθύρου
        isActive = false;
        gp.keyH.setEscPressed(false);
        if(ButtonSetter.getPlaySound())
            Menu.continuePlaying();

    }
}
