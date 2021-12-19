package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Παράθυρο για StartMenu
 *
 * @author Team Hack-You
 */
public final class Menu implements ActionListener {

    /*
     * Initialize μεταβλητών διαστάσεων
     */
    private final int X = 380;
    private final int Y = 300;
    private final int WIDTH = 200;
    private final int HEIGHT = 50;
    private int counter = 0;
    private ImageIcon icon3;
    private final ImageIcon title = new ImageIcon("src/main/resources/Title.png");

    static Sound music = new Sound();

    JFrame frame = new JFrame();
    JButton start = new JButton("Start Game");
    JButton how2play = new JButton("How to Play");
    JButton credits = new JButton("Show Credits");
    JButton description = new JButton("Game Description");
    /*Προσδιορισμός για τον αν παίζεται μουσική στο παιχνίδι ή όχι
    ώστε να απεικονιστεί η κατάσταση ήχου στο κουμπί*/
    JButton musicOn_Off = new JButton(String.format("Sound %s", ButtonSetter.playSound ? "off" : "on"));
    JLabel label = new JLabel();
    JLabel backgroundLabel = new JLabel();

    //Αρχικοποίηση εξαρτημένων παραθύρων
    UtilityFrame[] utilityFrames = new UtilityFrame[3];

    public Menu() {
        if (ButtonSetter.playSound)
            playMusic();
        // Εξατομίκευση παραθύρου
        frame.setTitle("Menu"); //setTitle of frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setSize(970, 850);
        frame.setVisible(true);
        frame.setLayout(null);
        frame.setIconImage(Main.icon.getImage());
        //Για να εμφανίζεται στο κέντρο της οθόνης του χρήστη
        frame.setLocationRelativeTo(null);

        setButton(start, Y);
        setButton(how2play, Y + 100);
        setButton(credits, Y + 200);
        setButton(description, Y + 300);
        setButton(musicOn_Off, Y + 400);
        //ButtonSetter.setButton(musicOn_Off, 0, 0, 50, 50, "Calibri", 10, this, 2);

        frame.add(start);
        frame.add(how2play);
        frame.add(credits);
        frame.add(description);
        frame.add(musicOn_Off);

        //Εισαγωγή τίτλου παιχνιδιού
        FrameSetter.scaleImgToLabel(label, 250, 0, 500, 300, title);
        frame.add(label);
        FrameSetter.scaleBackground(backgroundLabel, 970, 850);
        frame.add(backgroundLabel);
        //-------test changes end------//
    }

    /**
     * Μέθοδος δημιουργίας Κουμπιών
     */
    private void setButton(JButton button, int y) {
        counter++;
        button.setBounds(X, y, WIDTH, HEIGHT);
        button.setFocusable(false);
        if (counter % 2 == 1) {
            icon3 = new ImageIcon("src/main/resources/buttons/wood2.png");
        } else {
            icon3 = new ImageIcon("src/main/resources/buttons/wood1.png");
        }
        button.setIcon(icon3);
        button.setHorizontalTextPosition(JButton.CENTER);
        button.setFont(new Font("Calibri", Font.BOLD, 20));
        button.setForeground(Color.black);
        //button.setHorizontalAlignment(JButton.CENTER);
        button.addActionListener(this);
        //button.setFont(new Font("Calibri",Font.ITALIC,16));
    }

    /**
     * Ενέργεια όταν κάνουμε κλικ στο κουμπί
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        ButtonSetter.playSE();
        if (e.getSource() == musicOn_Off) {
            ButtonSetter.setPlaySound(!ButtonSetter.playSound);
            if (ButtonSetter.playSound) {
                playMusic();
            } else {
                stopMusic();
            }
            musicOn_Off.setText(String.format("Sound %s", ButtonSetter.playSound ? "off" : "on"));
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

    public static void continuePlaying() {
        music.play();
    }

    public static void playMusic() {
        music.setFile(0);
        music.play();
        music.loop();
    }

    public static void stopMusic() {
        music.stop();
    }


}