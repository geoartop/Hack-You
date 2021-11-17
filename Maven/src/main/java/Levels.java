import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Επιλογή Επίπεδου δυσκολίας
 *
 * @author Panagiotis Spanakis k synergates
 */
public class Levels implements ActionListener {

    /**Initialize μεταβλητών διαστάσεων*/
    private final int BX=225;
    private final int BY=200;
    private final int B_WIDTH=150;
    private final int B_HEIGHT=50;

    JFrame frame;
    JButton easy = new JButton("Easy");
    JButton medium = new JButton("Medium");
    JButton hard = new JButton("Hard");
    JLabel label = new JLabel();
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

        setButton(easy, BX, BY, B_WIDTH, B_HEIGHT);
        setButton(medium, BX, BY+100, B_WIDTH, B_HEIGHT);
        setButton(hard, BX, BY+200, B_WIDTH, B_HEIGHT);

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
    public void setButton(JButton button, int x, int y, int width, int height) {
        button.setBounds(x, y, width, height);
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
        //Κάθε κατηγορία λαβύρινθου να κάνει extend την κλάση Labyrinth!
        frame.dispose();
    }
}
