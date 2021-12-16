package game;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Παράθυρο επιλόγων που προκαλεί παύση του παιχνιδιού όταν εμφανίζεται
 */
public class Options implements ActionListener {

    public GamePanel gp;
    JFrame frame;
    JLabel backgroundLabel = new JLabel();
    JButton returnBack = new JButton("return");
    protected JButton showGuide = new JButton("show Guide");
    JButton restart = new JButton("restart");
    JButton end = new JButton("exit");
    protected static boolean isActive = false;
    static Guide guide;

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

        ButtonSetter.setButton(returnBack, 225, 200, 150, 50, "Calibri", 20, this, 2);
        ButtonSetter.setButton(showGuide, 225, 300, 150, 50, "Calibri", 20, this, 2);
        ButtonSetter.setButton(restart, 225, 400, 150, 50, "Calibri", 20, this, 2);
        ButtonSetter.setButton(end, 225, 500, 150, 50, "Calibri", 20, this, 2);

        frame.add(returnBack);
        frame.add(showGuide);
        frame.add(restart);
        frame.add(end);

        FrameSetter.scaleBackground(backgroundLabel, 600, 650);
        frame.add(backgroundLabel);
    }

    /**
     * Έλεγχος για τον αν υπάρχει ανοιχτό παράθυρο guide
     * Σε περίπτωση που υπάρχει το παράθυρο αυτό κλείνει
     */
    private void check() {
        if (guide == null)
            return;
        if (guide.getIsOpen())
            guide.closeFrame();
    }

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
        } else {
            System.exit(1);
        }
        //Για να μην κολλήσει το progressBar
        if (gp.labyrinthFrame.hasStarted) {
            gp.gameState = gp.playState;
            gp.labyrinthFrame.updateBar(0);
        }
        // Ενημερώνουμε το gamepanel για το κλείσιμο του παραθύρου
        isActive = false;
        gp.keyH.escPressed = false;
        if(ButtonSetter.playSound)
            Menu.continuePlaying();

    }
}