import javax.swing.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Επιλογή Επίπεδου δυσκολίας
 *
 * @author Panagiotis Spanakis k synergates
 */
public class Levels implements ActionListener {

    /**
     * Initialize μεταβλητών διαστάσεων
     */
    private final int BX = 225;
    private final int BY = 200;
    private final int B_WIDTH = 150;
    private final int B_HEIGHT = 50;

    JFrame frame;
    JButton easy = new JButton("Easy");
    JButton medium = new JButton("Medium");
    JButton hard = new JButton("Hard");
    JLabel label = new JLabel();

    /**
     * Θέλουμε να γνωρίζει η κλάση labyrinth το επίπεδο δυσκολίας που επίλεξε
     */
    protected static String difficulty="";

    public Levels() {
        // Εξατομίκευση παραθύρου
        frame = new JFrame(); //create frame
        frame.setTitle("Select Difficulty"); //setTitle of frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setSize(600, 600);
        frame.setVisible(true);
        frame.setLayout(null);
        frame.setIconImage(Main.icon.getImage());

        setButton(easy, BY);
        setButton(medium, BY + 100);
        setButton(hard, BY + 200);

        //Τοποθέτηση χρωμάτων στα κουμπιά
        easy.setForeground(Color.green);
        medium.setForeground(Color.orange);
        hard.setForeground(Color.red);

        label.setBounds(0, 0, 100, 50);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setText("Select Difficulty");

        /**Προσθήκη συστατικών*/
        frame.add(easy);
        frame.add(medium);
        frame.add(hard);
        frame.add(label);
    }

    /**
     * Μέθοδος δημιουργίας Κουμπιών
     */
    public void setButton(JButton button, int y) {
        button.setBounds(BX, y, B_WIDTH, B_HEIGHT);
        button.setFocusable(false);
        button.addActionListener(this);
        button.setHorizontalAlignment(JButton.CENTER);
        button.setFont(new Font("Arial", Font.ITALIC,20));
    }

    /**
     * Ενέργεια όταν κάνουμε κλικ στο κουμπί
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == easy) {
            //new EasyLabyrinths();
            difficulty = "easy";
        } else if (e.getSource() == medium) {
            //new MediumLabyrinths();
            difficulty = "medium";
        } else {
            //new HardLabyrinths();
            difficulty = "hard";
        }
        frame.dispose();
        /**Δημιουργία λαβύρινθου και καθορισμός δυσκολίας ερωτήσεων*/
        Labyrinth.setLabyrinth();
        Questions.setQuestionsDifficulty();
        SwingUtilities.invokeLater(LabyrinthFrame::new);
        //TODO Κάθε κατηγορία λαβύρινθου να κάνει extend την κλάση Labyrinth!

    }
}
