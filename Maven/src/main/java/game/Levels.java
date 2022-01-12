package game;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

/**
 * <p>Παράθυρο επιλογής επίπεδου δυσκολίας</p>
 *
 * @author Team Hack-You
 * @version 1.0
 */
public final class Levels implements ActionListener {

    private static final ImageIcon easy_icon = new ImageIcon("src/main/resources/buttons/wood1.png");
    private static final ImageIcon medium_icon = new ImageIcon("src/main/resources/buttons/iron2.jpg");
    private static final ImageIcon hard_icon = new ImageIcon("src/main/resources/buttons/gold2.png");

    private final JFrame frame;
    private final JButton easy = new JButton("Easy");
    private final JButton medium = new JButton("Medium");
    private final JButton hard = new JButton("Hard");

    private int y = 200;

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

        GraphicPane graphicPane = new GraphicPane("Choose difficulty", 600, 50, Main.mainColor, new Font("Times new Roman", Font.BOLD, 35));
        graphicPane.setBounds(0, 50, 600, 100);
        frame.add(graphicPane);

        setButton(easy);
        setButton(medium);
        setButton(hard);
        //Set Scaled Background
        JLabel backgroundLabel = new JLabel();
        FrameSetter.scaleBackground(backgroundLabel, 600, 600);
        frame.add(backgroundLabel);
    }

    /**
     * <p>setButton.</p>
     *
     * @param button a {@link JButton} object
     */
    private void setButton(JButton button) {
        ButtonSetter.setButton(button, 225, y, 150, 50, new Font("Calibri", Font.ITALIC, 22), this);
        if (button == easy) {
            button.setIcon(easy_icon);
        } else if (button == medium) {
            button.setIcon(medium_icon);
        } else {
            button.setIcon(hard_icon);
        }
        frame.add(button);
        y += 100;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        ButtonSetter.playSE();
        //Έλεγχος για το αν αλλάζει η δυσκολία έτσι ώστε να ξαναγεμίζουν οι απαραίτητες λίστες μόνο όταν είναι απαραίτητο
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
