package game;

import com.google.common.annotations.VisibleForTesting;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * <p>Παράθυρο για StartMenu</p>
 *
 * @author Team Hack-You
 * @version 1.0
 */
public final class Menu implements ActionListener {

    private static final ImageIcon title = new ImageIcon("src/main/resources/Title.png");
    private static final ImageIcon thiseas = new ImageIcon("src/main/resources/thiseas2/thiseaswalkingdown8.png");
    private static final ImageIcon minotaur = new ImageIcon("src/main/resources/minotaur/minotaurwin.png");

    private static final Sound music = new Sound();

    private final JFrame frame = new JFrame();
    private final JButton start = new JButton("Start Game");
    private final JButton how2play = new JButton("How to Play");
    private final JButton credits = new JButton("Show Credits");
    private final JButton description = new JButton("Game Description");
    /**
     * Προσδιορισμός για τον αν παίζεται μουσική
     * στο παιχνίδι ή όχι ώστε να απεικονιστεί η κατάσταση ήχου στο κουμπί
     */
    private final JButton musicOn_Off = new JButton(String.format("Sound %s", ButtonSetter.getPlaySound() ? "off" : "on"));
    private final JLabel label = new JLabel();
    private final JLabel backgroundLabel = new JLabel();

    //Αρχικοποίηση εξαρτημένων παραθύρων
    private final UtilityFrame[] utilityFrames = new UtilityFrame[3];

    private int y = 250;

    /**
     * <p>Setter for <code>how2play</code> enabled status</p>
     *
     * @param status a boolean
     */
    public void setHow2playStatus(boolean status) {
        how2play.setEnabled(status);
    }

    /**
     * <p>Setter for <code>credits</code> enabled status</p>
     *
     * @param status a boolean
     */
    public void setCreditsStatus(boolean status) {
        credits.setEnabled(status);
    }

    /**
     * <p>Setter for <code>description</code> enabled status</p>
     *
     * @param status a boolean
     */
    public void setDescriptionStatus(boolean status) {
        description.setEnabled(status);
    }

    /**
     * <p>Constructor for Menu.</p>
     */
    public Menu() {
        if (checkMusic()) {
            playMusic();
        }
        // Εξατομίκευση παραθύρου
        FrameSetter.setFrame(frame, "Menu", 970, 780);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setButton(start);
        setButton(how2play);
        setButton(credits);
        setButton(description);
        setButton(musicOn_Off);

        JLabel thLabel = new JLabel();
        JLabel mLabel = new JLabel();

        FrameSetter.scaleImgToLabel(thLabel, 380, 170, 90, 70, thiseas);
        frame.add(thLabel);

        FrameSetter.scaleImgToLabel(mLabel, 465, 140, 120, 100, minotaur);
        frame.add(mLabel);

        //Εισαγωγή τίτλου παιχνιδιού
        FrameSetter.scaleImgToLabel(label, 235, 50, 500, 100, title);
        frame.add(label);
        //Εισαγωγή background
        FrameSetter.scaleBackground(backgroundLabel, 970, 780);
        frame.add(backgroundLabel);
    }

    /**
     * <p>setButton.</p>
     *
     * @param button a {@link JButton} object
     */
    private void setButton(JButton button) {
        ButtonSetter.setButton(button, 380, y, 200, 50, Main.mainFont, this);
        frame.add(button);
        y += 100;
    }

    /** {@inheritDoc} */
    @Override
    public void actionPerformed(ActionEvent e) {
        ButtonSetter.playSE();
        if (e.getSource() == musicOn_Off) {
            ButtonSetter.setPlaySound(!ButtonSetter.getPlaySound());
            if (ButtonSetter.getPlaySound()) {
                playMusic();
            } else {
                stopMusic();
            }
            musicOn_Off.setText(String.format("Sound %s", ButtonSetter.getPlaySound() ? "off" : "on"));
            return;
        }

        if (e.getSource() == start) {
            new Levels();
            frame.dispose();
            //Έλεγχος για το αν υπάρχουν ανοιχτά utilityFrames πριν την έναρξη του παιχνιδιού
            for (UtilityFrame utilityFrame : utilityFrames) {
                if (utilityFrame == null) {
                    continue;
                }
                if (utilityFrame.getIsOpen()) {
                    utilityFrame.closeFrame();
                }
            }
        } else if (e.getSource() == how2play) {
            how2play.setEnabled(false);
            utilityFrames[0] = new Guide(this);
        } else if (e.getSource() == credits) {
            credits.setEnabled(false);
            utilityFrames[1] = new Credits(this);
        } else {
            description.setEnabled(false);
            utilityFrames[2] = new Description(this);
        }
    }

    /**
     * <p>continuePlaying</p>
     */
    static void continuePlaying() {
        music.play();
    }

    /**
     * <p>playMusic</p>
     */
    static void playMusic() {
        music.setFile(0);
        music.play();
        music.loop();
    }

    static boolean checkMusic() {
        return !music.isPlaying() && ButtonSetter.getPlaySound();
    }

    /**
     * <p>musicIsPlaying.</p>
     *
     * @return a boolean
     */
    @VisibleForTesting
    public static boolean musicIsPlaying() {
        return music.isPlaying();
    }

    /**
     * <p>stopMusic</p>
     */
    static void stopMusic() {
        music.stop();
    }


}
