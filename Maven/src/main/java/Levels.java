import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Επιλογή Επίπεδου δυσκολίας
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
    private final ImageIcon easy_icon = new ImageIcon("src/main/resources/wood1.png");
    private final ImageIcon medium_icon = new ImageIcon("src/main/resources/iron2.jpg");
    private final ImageIcon hard_icon = new ImageIcon("src/main/resources/gold2.png");

    JFrame frame;
    JButton easy = new JButton("Easy");
    JButton medium = new JButton("Medium");
    JButton hard = new JButton("Hard");
    //-------test changes------//
    JLabel label2 = new JLabel();
    // -------test changes end------//

    /*
     * Θέλουμε να γνωρίζει η κλάση LabyrinthFrame το επίπεδο δυσκολίας που επίλεξε
     */
    protected static String difficulty = "";

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
        //Για να εμφανίζεται στο κέντρο της οθόνης του χρήστη
        frame.setLocationRelativeTo(null);

        setButton(easy, BY);
        setButton(medium, BY + 100);
        setButton(hard, BY + 200);

        easy.setIcon(easy_icon);
        medium.setIcon(medium_icon);
        hard.setIcon(hard_icon);

        /*Προσθήκη συστατικών*/
        frame.add(easy);
        frame.add(medium);
        frame.add(hard);
        //-------test changes------//
        //Set Scaled Background
        Image img = Main.background.getImage();
        Image temp = img.getScaledInstance(585, 600, Image.SCALE_SMOOTH);
        ImageIcon back = new ImageIcon(temp);
        label2.setIcon(back);
        label2.setBounds(0, 0, 600, 600);
        frame.add(label2);
        //-------test changes end------//
    }

    /**
     * Μέθοδος δημιουργίας Κουμπιών
     */
    public void setButton(JButton button, int y) {
        button.setBounds(BX, y, B_WIDTH, B_HEIGHT);
        button.setFocusable(false);
        button.addActionListener(this);
        button.setHorizontalAlignment(JButton.CENTER);
        button.setHorizontalTextPosition(JButton.CENTER);
        button.setForeground(Color.BLACK);
        button.setFont(new Font("Calibri", Font.ITALIC, 22));
    }

    /**
     * Ενέργεια όταν κάνουμε κλικ στα κουμπιά
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
        /*Δημιουργία λαβύρινθου και καθορισμός δυσκολίας ερωτήσεων*/
        LabyrinthFrame.setLabyrinth();
        Questions.setQuestionsDifficulty();
        SwingUtilities.invokeLater(LabyrinthFrame::new);
        //TODO Κάθε κατηγορία λαβύρινθου να κάνει extend την κλάση Labyrinth!

    }
}
