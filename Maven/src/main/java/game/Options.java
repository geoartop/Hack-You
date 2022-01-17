package game;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * <p>Παράθυρο επιλόγων που προκαλεί παύση του παιχνιδιού όταν εμφανίζεται.</p>
 *
 * @author Team Hack-You
 * @version 1.0
 */
public final class Options implements ActionListener {

    private final GamePanel gp;
    private final JFrame frame;
    private final JLabel backgroundLabel = new JLabel();
    private final JButton sound = new JButton("Sound Settings");
    private final JButton returnBack = new JButton("Return");
    private final JButton showGuide = new JButton("How to Play");
    private final JButton restart = new JButton("Restart");
    private final JButton back2menu = new JButton("Back to Menu");
    private final JButton end = new JButton("Exit");
    private static boolean isActive = false;
    /**
     * <p>Αρχικοποίηση εξαρτημένων παραθύρων <br>
     * 0 for guide <br>
     * 1 for SoundSettings</p>
     */
    private final UtilityFrame[] utilityFrames = new UtilityFrame[2];

    private int y = 150;


    /**
     * <p>set <code>showGuide</code> enabled status</p>
     *
     * @param status a boolean
     */
    public void setShowGuideStatus(boolean status) {
        showGuide.setEnabled(status);
    }

    /**
     * <p>Setter for <code>how2play</code> enabled status.</p>
     *
     * @param status a boolean
     */
    public void setSoundStatus(boolean status) {
        sound.setEnabled(status);
    }

    /**
     * <p>Constructor for Options.</p>
     *
     * @param gp a {@link game.GamePanel} object
     */
    public Options(GamePanel gp) {
        isActive = true;
        this.gp = gp;
        frame = new JFrame();
        FrameSetter.setFrame(frame, "Options", 650, 750);
        //Θέτω το κουμπί της εξόδου να κάνει αυτόματα click το return για να μην κολλήσει η ροή του LabyrinthFrame
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                returnBack.doClick();
            }
        });
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        GraphicPane graphicPane = new GraphicPane("Options", 650, 100, Main.mainColor, new Font("Times new Roman", Font.BOLD + Font.ITALIC, 50));
        graphicPane.setBounds(0, 0, 650, 150);
        frame.add(graphicPane);

        setButton(returnBack);
        setButton(sound);
        setButton(showGuide);
        setButton(restart);
        setButton(back2menu);
        setButton(end);

        FrameSetter.scaleBackground(backgroundLabel, 650, 750);
        frame.add(backgroundLabel);
    }

    /**
     * <p>setButton.</p>
     *
     * @param button a {@link JButton} object
     */
    private void setButton(JButton button) {
        ButtonSetter.setButton(button, 235, y, 200, 50, Main.mainFont, this);
        frame.add(button);
        y += 100;
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
     * {@inheritDoc}
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        ButtonSetter.playSE();
        if (e.getSource() == returnBack) {
            frame.dispose();
        } else if (e.getSource() == showGuide) {
            utilityFrames[0] = new Guide(this);
            showGuide.setEnabled(false);
            return;
        } else if (e.getSource() == restart) {
            gp.labyrinthFrame.closeFrame();
            LabyrinthFrame.setRestartStatus(true);
            SwingUtilities.invokeLater(LabyrinthFrame::new);
            frame.dispose();
            Quiz.clearIndexes();
        } else if (e.getSource() == back2menu) {
            gp.labyrinthFrame.closeFrame();
            SwingUtilities.invokeLater(Menu::new);
            Quiz.clearIndexes();
            frame.dispose();
        } else if (e.getSource() == sound) {
            utilityFrames[1] = new SoundSettings(this);
            sound.setEnabled(false);
            return;
        } else {
            Main.exit();
        }
        //Κλείσιμο όλων των extra ανοιχτών παραθύρων
        UtilityFrame.check(utilityFrames);
        //Για να μην κολλήσει το progressBar
        if (gp.labyrinthFrame.getHasStarted()) {
            gp.setGameState(GamePanel.playState);
            gp.labyrinthFrame.updateBar(0);
        }
        // Ενημερώνουμε το gamepanel για το κλείσιμο του παραθύρου
        isActive = false;
        gp.keyH.setEscPressed(false);
        if (ButtonSetter.getPlaySound() && e.getSource() != back2menu) {
            Menu.continuePlaying();
        }

    }

}
