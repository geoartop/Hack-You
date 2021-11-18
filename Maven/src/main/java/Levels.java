import javax.swing.*;
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
    private ImageIcon Icon = new ImageIcon("src/main/resources/maze-icon.png");
    /**
     * Θέλουμε να γνωρίζει η κλάση labyrinth το επίπεδο δυσκολίας που επίλεξε
     */
    protected static String difficulty;

    public Levels() {
        // Εξατομίκευση παραθύρου
        frame = new JFrame(); //create frame
        frame.setTitle("Select Difficulty"); //setTitle of frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setSize(600, 600);
        frame.setVisible(true);
        frame.setLayout(null);
        frame.setIconImage(Icon.getImage());

        setButton(easy, BY);
        setButton(medium, BY + 100);
        setButton(hard, BY + 200);

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
        /**Δημιουργία λαβύρινθου και καθορισμός δυσκολίας ερωτήσεων*/
        Labyrinth.setLabyrinth();
        Questions.setQuestionsDifficulty();
        new Labyrinth();
        //Κάθε κατηγορία λαβύρινθου να κάνει extend την κλάση Labyrinth!
        frame.dispose();
    }
}
