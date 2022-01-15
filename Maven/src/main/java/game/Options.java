package game;

import com.google.common.annotations.VisibleForTesting;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.SwingUtilities;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.event.ChangeListener;

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
    private final JButton returnBack = new JButton("return");
    private final JButton showGuide = new JButton("how to play");
    private final JButton restart = new JButton("restart");
    private final JButton back2menu = new JButton("back to Menu");
    private final JButton end = new JButton("exit");
    private static boolean isActive = false;
    private Guide guide;
    private static final JSlider slider = new JSlider(-30, 6, (-30 + 6) / 2);

    /**
     * <code>slider</code> minimum value
     */
    static final int min = slider.getMinimum();
    /**
     * <code>slider</code> maximum value
     */
    static final int max = slider.getMaximum();
    private int y = 200;


    /**
     * <p>set <code>showGuide</code> enabled status</p>
     *
     * @param status a boolean
     */
    public void setShowGuideStatus(boolean status) {
        showGuide.setEnabled(status);
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
        frame.setTitle("Options"); //setTitle of frame
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

        slider.setBounds(200, 120, 300, 100);
        slider.setOpaque(false);
        slider.setEnabled(true);
        JLabel label = new JLabel();
        label.setFont(new Font("Calibri", Font.BOLD, 22));
        label.setBounds(100, 120, 200, 100);
        label.setText("Volume:");
        frame.add(label);
        frame.add(slider);

        if (!ButtonSetter.getPlaySound()) {
            slider.setEnabled(false);
        }

        setButton(returnBack);
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
     * <p>check</p>
     *
     * <p>Έλεγχος για τον αν υπάρχει ανοιχτό παράθυρο guide <br>
     * Σε περίπτωση που υπάρχει το παράθυρο αυτό κλείνει</p>
     */
    private void check() {
        if (guide == null) {
            return;
        }
        if (guide.getIsOpen()) {
            guide.closeFrame();
        }
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
            guide = new Guide(this);
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
        } else {
            Main.exit();
        }
        check();
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

    /**
     * <p>Setter for the field's <code>slider</code> value .</p>
     *
     * @param value an int
     */
    @VisibleForTesting
    public static void setSliderValue(int value) {
        slider.setValue(value);
    }

    /**
     * <p>Getter for the field's <code>slider</code> value .</p>
     *
     * @return an int
     */
    static int getSliderValue() {
        return slider.getValue();
    }

    /**
     * <p>Check if slider is still adjusting.</p>
     *
     * @return a boolean
     */
    static boolean sliderIsAdjusting() {
        return slider.getValueIsAdjusting();
    }

    /**
     * <p>add ChangeListener to slider.</p>
     *
     * @param changeListener a {@link ChangeListener} object
     */
    static void sliderAddChangeListener(ChangeListener changeListener) {
        slider.addChangeListener(changeListener);
    }

}