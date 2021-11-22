import javax.swing.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/*
 * WARNING
 *  TODO Να φτιάξουμε νέα κλάση για το frame του παιχνιδιού, για το panel που θα παίζει ο λαβύρινθος
 */


/**
 * @author Panagiotis Spanakis kai synergates
 * <p> Κλάση όπου θα τρέχει ο λαβίρυνθος
 */
public class LabyrinthFrame implements KeyListener, ActionListener {

    //protected static File file;
    /**
     * ProgressBar
     */
    JFrame frame;
    static JProgressBar bar = new JProgressBar(0, 100);
    JButton start = new JButton("start");
    JButton testQuestionFrame=new JButton("try me");
    /* JButton pause = new JButton("pause");
    JButton goOn = new JButton("continue");*/
    //-------test changes------//
    JLabel label = new JLabel();
    //-------test changes end------//

    //Μεταβλητές χρήσιμες για την λειτουργία του progressBar
    protected static boolean go = true; // Για το αν συνεχίζει το παιχνίδι ή βρίσκεται σε pause
    private int pause_count = 0; //Για το πόσες φορές έχει πατήσει το spacebar
    private boolean hasStarted = false; // Για το αν έχει αρχίσει το παιχνίδι

    //Μεταβλητές για πόσο χρόνο ο παίκτης θα κερδίζει χάνει ανάλογα με την απάντησή του στις ερωτήσεις
    protected static int for_correct;
    protected static int for_wrong;

    //--------------------------------------------------------------------------------------//

    protected static void setLabyrinth() {
        switch (Levels.difficulty) {
            case "easy":
                //file=EasyLabyrinth.txt;
                for_correct=5;
                for_wrong=-2;
                break;
            case "medium":
                //file=MediumLabyrinth.txt;
                for_correct=5;
                for_wrong=-3;
                break;
            default:
                //file=HardLabyrinth.txt;
                for_correct=3;
                for_wrong=-5;
                break;
        }

    }

    /**
     * PROBLEM !
     * TODO FIX LAGGING PROGRESS BAR
     * <p> UPDATE -> FIXED Μέσα από Threading
     */

    private void createFrame(){
        frame=new JFrame();
        frame.setTitle("Labyrinth"); //setTitle of frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setSize(600, 600);
        frame.setVisible(true);
        frame.setLayout(null);
        frame.setIconImage(Main.icon.getImage());
        //Για να εμφανίζεται στο κέντρο της οθόνης του χρήστη
        frame.setLocationRelativeTo(null);
    }

    private void createBar(){
        bar.setValue(100);
        bar.setBounds(0, 0, 600, 50);
        bar.setStringPainted(true);
        bar.setFont(new Font("Arial", Font.BOLD, 25));
        bar.setForeground(Color.red);
        bar.setBackground(Color.black);
        bar.setVisible(false);
    }


    public LabyrinthFrame() {
        createFrame();
        createBar();
        setButton(start, 500);
        start.setBackground(Color.green);
        /*setButton(pause, 400);
        setButton(goOn, 500);
        goOn.setEnabled(false);*/

        //Key Bind
        frame.addKeyListener(this);

        frame.add(bar);
        frame.add(start);

        setButton(testQuestionFrame,400);
        testQuestionFrame.setEnabled(false);
        frame.add(testQuestionFrame);
        /*this.add(pause);
        this.add(goOn);*/
        //-------test changes------//
        //label.setIcon(Main.background);
        //label.setBounds(0,0,1000,1000);
        //this.add(label);
        //-------test changes end------//
    }

    /**
     * Μέθοδος λειτουργίας progressBar
     */
    private static void fill(int flg) {
        int counter = flg;

        while (counter > 0) {
            if (!go) {
                go = true;
                return;
            }
            bar.setValue(counter);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "A problem has occurred", "Error", JOptionPane.ERROR_MESSAGE);
            }
            counter--;
        }
        bar.setString("Game Over");
    }

    public void setButton(JButton button, int y) {
        button.setBounds(250, y, 100, 50);
        button.setFocusable(false);
        button.addActionListener(this);
        button.setHorizontalAlignment(JButton.CENTER);
        button.setFont(new Font("Calibri", Font.ITALIC, 20));
    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE && hasStarted) {
            pause_count++;
            if (pause_count % 2 != 0) {
                go = false;
                return;
            }
            Thread fill_bar2 = new Thread(() -> fill(bar.getValue()));
            fill_bar2.start();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    protected static void updateBar(int time){
        Thread fill_bar = new Thread(() -> fill(bar.getValue()+time));
        fill_bar.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == start) {
            bar.setVisible(true);
            Thread fill_bar = new Thread(() -> fill(100));
            fill_bar.start();
            start.setEnabled(false);
            hasStarted = true;
            testQuestionFrame.setEnabled(true);
        }else if(e.getSource() == testQuestionFrame){
            //Ο χρόνος σταματάει μέχρι να απαντηθεί η ερώτηση
            go=false;
            SwingUtilities.invokeLater(QuizFrame::new);

        }
    }
}