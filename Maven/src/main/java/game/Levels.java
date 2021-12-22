package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

/**
 * Παράθυρο επιλογής επίπεδου δυσκολίας
 *
 * @author Team Hack-You
 * @version 1.0
 */
public final class Levels implements ActionListener {

    /*
     * Initialize μεταβλητών διαστάσεων
     */
    private final int BX = 225;
    private final int BY = 200;
    private final int B_WIDTH = 150;
    private final int B_HEIGHT = 50;
    private static final ImageIcon easy_icon = new ImageIcon("src/main/resources/buttons/wood1.png");
    private static final ImageIcon medium_icon = new ImageIcon("src/main/resources/buttons/iron2.jpg");
    private static final ImageIcon hard_icon = new ImageIcon("src/main/resources/buttons/gold2.png");

    private final JFrame frame;
    private final JButton easy = new JButton("Easy");
    private final JButton medium = new JButton("Medium");
    private final JButton hard = new JButton("Hard");

    private final JLabel backgroundLabel = new JLabel();

    //Θέλουμε να γνωρίζει η κλάση LabyrinthFrame το επίπεδο δυσκολίας που επίλεξε ο παίκτης
    private static String difficulty = "";

    /**
     * <p>Getter for the field <code>difficulty</code>.</p>
     *
     * @return a {@link java.lang.String} object
     */
    public static String getDifficulty() {
        return difficulty;
    }

    /**
     * <p>Constructor for Levels.</p>
     */
    public Levels() {
        // Εξατομίκευση παραθύρου
        frame = new JFrame(); //create frame
        FrameSetter.setFrame(frame, "Select Difficulty", 600, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ButtonSetter.setButton(easy, BX, BY, B_WIDTH, B_HEIGHT, new Font("Calibri", Font.ITALIC, 22), this);
        ButtonSetter.setButton(medium, BX, BY + 100, B_WIDTH, B_HEIGHT, new Font("Calibri", Font.ITALIC, 22), this);
        ButtonSetter.setButton(hard, BX, BY + 200, B_WIDTH, B_HEIGHT, new Font("Calibri", Font.ITALIC, 22), this);

        easy.setIcon(easy_icon);
        medium.setIcon(medium_icon);
        hard.setIcon(hard_icon);

        GraphicPane graphicPane = new GraphicPane("Choose difficulty", 600, 50, Main.mainColor, new Font("Times new Roman", Font.BOLD, 35));
        graphicPane.setBounds(0, 50, 600, 100);
        frame.add(graphicPane);

        //Προσθήκη συστατικών
        frame.add(easy);
        frame.add(medium);
        frame.add(hard);
        //Set Scaled Background
        FrameSetter.scaleBackground(backgroundLabel, 600, 600);
        frame.add(backgroundLabel);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        ButtonSetter.playSE();
        String diff;
        if (e.getSource() == easy) {
            diff = "Easy";
        } else if (e.getSource() == medium) {
            diff = "Medium";
        } else {
            diff = "Hard";
        }
        frame.dispose();
        //Έλεγχος για τον αν ο παίκτης ξαναεπιλέγει το ίδιο επίπεδο ή όχι
        if (!difficulty.equals(diff)) {
            difficulty = diff;
            System.out.println(diff);
            //Προετοιμασία απαραίτητων μεταβλητών για τη λειτουργία του λαβυρίνθου
            LabyrinthFrame.setLabyrinth();
            try {
                //"Άδειασμα" λιστών για την επαναχρησιμοποίησή τους
                Quiz.clearLists();
                //Προετοιμασία ερωτήσεων
                Quiz.readQuestions();
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            }
        }
        SwingUtilities.invokeLater(LabyrinthFrame::new);
    }
}
