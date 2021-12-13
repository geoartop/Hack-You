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
 */
public class Levels implements ActionListener {

    /*
     * Initialize μεταβλητών διαστάσεων
     */
    private final int BX = 225;
    private final int BY = 200;
    private final int B_WIDTH = 150;
    private final int B_HEIGHT = 50;
    private final ImageIcon easy_icon = new ImageIcon("src/main/resources/buttons/wood1.png");
    private final ImageIcon medium_icon = new ImageIcon("src/main/resources/buttons/iron2.jpg");
    private final ImageIcon hard_icon = new ImageIcon("src/main/resources/buttons/gold2.png");

    JFrame frame;
    JButton easy = new JButton("Easy");
    JButton medium = new JButton("Medium");
    JButton hard = new JButton("Hard");

    JLabel backgroundLabel = new JLabel();

    //Θέλουμε να γνωρίζει η κλάση LabyrinthFrame το επίπεδο δυσκολίας που επίλεξε ο παίκτης
    protected static String difficulty = "";

    public Levels() {
        // Εξατομίκευση παραθύρου
        frame = new JFrame(); //create frame
        FrameSetter.setFrame(frame,"Select Difficulty",600,600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ButtonSetter.setButton(easy,BX,BY,B_WIDTH,B_HEIGHT,"Calibri",22,this,2);
        ButtonSetter.setButton(medium,BX,BY+100,B_WIDTH,B_HEIGHT,"Calibri",22,this,2);
        ButtonSetter.setButton(hard,BX,BY+200,B_WIDTH,B_HEIGHT,"Calibri",22,this,2);

        easy.setIcon(easy_icon);
        medium.setIcon(medium_icon);
        hard.setIcon(hard_icon);

        //Προσθήκη συστατικών
        frame.add(easy);
        frame.add(medium);
        frame.add(hard);
        //Set Scaled Background
        FrameSetter.scaleBackground(backgroundLabel,600,600);
        frame.add(backgroundLabel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ButtonSetter.playSE();
        if (e.getSource() == easy) {
            //new EasyLabyrinths();
            difficulty = "Easy";
        } else if (e.getSource() == medium) {
            //new MediumLabyrinths();
            difficulty = "Medium";
        } else {
            //new HardLabyrinths();
            difficulty = "Hard";
        }
        frame.dispose();
        //Δημιουργία λαβύρινθου και καθορισμός δυσκολίας ερωτήσεων
        LabyrinthFrame.setLabyrinth();
        try {
            Quiz.readQuestions();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        SwingUtilities.invokeLater(LabyrinthFrame::new);
        //TODO Κάθε κατηγορία λαβύρινθου να κάνει extend την κλάση Labyrinth!

    }
}