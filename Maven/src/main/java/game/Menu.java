package game;

import com.google.common.annotations.VisibleForTesting;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;
import javax.imageio.ImageIO;
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

    private static BufferedImage title;
    private static BufferedImage thiseas;
    private static BufferedImage minotaur;

    private static final Sound music = new Sound();

    private final JFrame frame = new JFrame();
    private final JButton start = new JButton("Start Game");
    private final JButton how2play = new JButton("How to Play");
    private final JButton credits = new JButton("Credits");
    private final JButton description = new JButton("Description");
    /**
     * Προσδιορισμός για τον αν παίζεται μουσική <br>
     * στο παιχνίδι ή όχι ώστε να απεικονιστεί η κατάσταση ήχου στο κουμπί
     */
    private final JButton sound = new JButton("Sound Settings");

    //Αρχικοποίηση εξαρτημένων παραθύρων
    private final UtilityFrame[] utilityFrames = new UtilityFrame[4];

    private int y = 250;

    /**
     * <p>Setter for <code>how2play</code> enabled status.</p>
     *
     * @param status a boolean
     */
    public void setHow2playStatus(boolean status) {
        how2play.setEnabled(status);
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
     * <p>Setter for <code>credits</code> enabled status.</p>
     *
     * @param status a boolean
     */
    public void setCreditsStatus(boolean status) {
        credits.setEnabled(status);
    }

    /**
     * <p>Setter for <code>description</code> enabled status.</p>
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
        FrameSetter.setFrame(frame, "Menu", 970, 770);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setButton(start);
        setButton(how2play);
        setButton(credits);
        setButton(description);
        setButton(sound);

        JLabel thLabel = new JLabel();
        JLabel mLabel = new JLabel();

        FrameSetter.scaleImgToLabel(thLabel, 380, 170, 90, 70, thiseas);
        frame.add(thLabel);

        FrameSetter.scaleImgToLabel(mLabel, 465, 140, 120, 100, minotaur);
        frame.add(mLabel);

        //Εισαγωγή τίτλου παιχνιδιού
        JLabel titleLabel = new JLabel();
        FrameSetter.scaleImgToLabel(titleLabel, 235, 50, 500, 100, title);
        frame.add(titleLabel);
        //Εισαγωγή background
        JLabel backgroundLabel = new JLabel();
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
        } else if (e.getSource() == description) {
            description.setEnabled(false);
            utilityFrames[2] = new Description(this);
        } else {
            sound.setEnabled(false);
            utilityFrames[3] = new SoundSettings(this);
        }
    }

    /**
     * <p>continuePlaying</p>
     */
    static void continuePlaying() {
        music.flush();
        music.play();
        music.loop();
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

    /**
     * <p>setup.</p>
     */
    static void setup() {
        try {
            title = ImageIO.read(Objects.requireNonNull(FrameSetter.class.getResourceAsStream("/Title.png")));
            thiseas = ImageIO.read(Objects.requireNonNull(FrameSetter.class.getResourceAsStream("/thiseas2/thiseaswalkingdown8.png")));
            minotaur = ImageIO.read(Objects.requireNonNull(FrameSetter.class.getResourceAsStream("/minotaur/minotaurwin.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
