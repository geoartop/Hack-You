package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.BatchUpdateException;

/**
 * Παράθυρο για StartMenu
 *
 * @author Team Hack-You
 * @version 1.0
 */
public final class Menu implements ActionListener {

    /*
     * Initialize μεταβλητών διαστάσεων
     */
    private final int X = 380;
    private final int Y = 300;
    private final int WIDTH = 200;
    private final int HEIGHT = 50;

    private static final ImageIcon title = new ImageIcon("src/main/resources/Title.png");
    private static final ImageIcon thiseas = new ImageIcon("src/main/resources/thiseas2/thiseaswalkingdown8.png");
    private static final ImageIcon minotaur = new ImageIcon("src/main/resources/minotaur/minotaurwin.png");

    static Sound music = new Sound();

    private final JFrame frame = new JFrame();
    final JButton start = new JButton("Start Game");
    final JButton how2play = new JButton("How to Play");
    final JButton credits = new JButton("Show Credits");
    final JButton description = new JButton("Game Description");
    /* Προσδιορισμός για τον αν παίζεται μουσική στο παιχνίδι ή όχι
    ώστε να απεικονιστεί η κατάσταση ήχου στο κουμπί */
    final JButton musicOn_Off = new JButton(String.format("Sound %s", ButtonSetter.getPlaySound() ? "off" : "on"));
    final JLabel label = new JLabel();
    final JLabel backgroundLabel = new JLabel();

    //Αρχικοποίηση εξαρτημένων παραθύρων
    final UtilityFrame[] utilityFrames = new UtilityFrame[3];

    /**
     * <p>Constructor for Menu.</p>
     */
    public Menu() {
        if (ButtonSetter.getPlaySound())
            playMusic();
        // Εξατομίκευση παραθύρου
        FrameSetter.setFrame(frame, "Menu", 970, 850);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setButton(start, Y);
        setButton(how2play, Y + 100);
        setButton(credits, Y + 200);
        setButton(description, Y + 300);
        setButton(musicOn_Off, Y + 400);

        frame.add(start);
        frame.add(how2play);
        frame.add(credits);
        frame.add(description);
        frame.add(musicOn_Off);

        JLabel thLabel = new JLabel();
        JLabel mLabel = new JLabel();

        FrameSetter.scaleImgToLabel(thLabel, 380, 210, 90, 70, thiseas);
        frame.add(thLabel);

        FrameSetter.scaleImgToLabel(mLabel, 465, 180, 120, 100, minotaur);
        frame.add(mLabel);

        //Εισαγωγή τίτλου παιχνιδιού
        FrameSetter.scaleImgToLabel(label, 250, 0, 500, 280, title);
        frame.add(label);
        //Εισαγωγή background
        FrameSetter.scaleBackground(backgroundLabel, 970, 850);
        frame.add(backgroundLabel);
    }

    /**
     * Μέθοδος δημιουργίας Κουμπιών
     */
    private void setButton(JButton button, int y) {
        ButtonSetter.setButton(button, X, y, WIDTH, HEIGHT, new Font("Calibri", Font.BOLD, 20), this);
    }

    /**
     * {@inheritDoc}
     */
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
                if (utilityFrame == null)
                    continue;
                if (utilityFrame.getIsOpen())
                    utilityFrame.closeFrame();
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

    /**
     * <p>stopMusic</p>
     */
    static void stopMusic() {
        music.stop();
    }


}
