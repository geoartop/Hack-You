package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;

/*
 * WARNING
 *  TODO Να φτιάξουμε νέα κλάση για το frame του παιχνιδιού, για το panel που θα παίζει ο λαβύρινθος
 */


/**
 * @author Team Hack-You
 * <p> Κλάση όπου θα τρέχει ο λαβίρυνθος
 */
public class LabyrinthFrame implements KeyListener, ActionListener {

    /**
     * ProgressBar
     */
    //Δηλώνω static το frame έτσι ώστε να μπορεί να ανοιγοκλείνει από τα Options
    protected static JFrame frame;
    static JProgressBar bar;
    GamePanel gamePanel = new GamePanel();


    JButton start = new JButton("start");
    JButton testQuestionFrame = new JButton("try me");


    //Μεταβλητές χρήσιμες για τη λειτουργία του progressBar
    protected static boolean go = true; // Για το αν συνεχίζει το παιχνίδι ή βρίσκεται σε pause
    private int pause_count = 0; //Για το πόσες φορές έχει πατήσει το spacebar
    private boolean hasStarted = false; // Για το αν έχει αρχίσει το παιχνίδι

    //Μεταβλητές για πόσο χρόνο ο παίκτης θα κερδίζει χάνει ανάλογα με την απάντησή του στις ερωτήσεις
    protected static int for_correct;
    protected static int for_wrong;
    //Πόσο χρόνο σε seconds θα έχει ο παίκτης
    private static int time;

    //Δηλώνω static και τα Threads ώστε να κλείνουν μαζί με το frame
    private static Thread fill_bar;

    //--------------------------------------------------------------------------------------//

    protected static void setLabyrinth() {
        switch (Levels.difficulty) {
            case "Easy":
                //file=EasyLabyrinth.txt;
                time = 200;
                for_correct = 5;
                for_wrong = -2;
                break;
            case "Medium":
                //file=MediumLabyrinth.txt;
                time = 150;
                for_correct = 5;
                for_wrong = -3;
                break;
            default:
                time = 10;
                //file=HardLabyrinth.txt;
                for_correct = 3;
                for_wrong = -5;
                break;
        }

    }


    private void createFrame() {
        frame = new JFrame();
        frame.setTitle("Labyrinth"); //setTitle of frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(true);
        frame.setSize(780, 700);
        frame.setVisible(true);
        frame.setLayout(new BorderLayout());
        frame.setIconImage(Main.icon.getImage());
        //Για να εμφανίζεται στο κέντρο της οθόνης του χρήστη
        frame.setLocationRelativeTo(null);
    }

    private void createBar() {
        bar = new JProgressBar(0, time);
        bar.setValue(time);
        bar.setStringPainted(true);
        //Θέτω το μέγεθος της progressBar
        bar.setPreferredSize(new Dimension(600,50));
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
        start.setFont(new Font("Calibri", Font.ITALIC, 25));

        //Key Bind
        frame.addKeyListener(this);

        frame.add(bar, BorderLayout.NORTH);
        frame.add(start, BorderLayout.SOUTH);

        setButton(testQuestionFrame, 400);
        testQuestionFrame.setEnabled(false);
        frame.add(testQuestionFrame);
        frame.add(gamePanel);

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
            bar.setString(String.format("%d seconds left",counter));
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
        SwingUtilities.invokeLater(DeathFrame::new);
        frame.dispose();
    }

    private void setButton(JButton button, int y) {
        button.setBounds(250, y, 100, 50);
        button.setFocusable(false);
        button.setHorizontalAlignment(JButton.CENTER);
        button.setFont(new Font("Calibri", Font.ITALIC, 20));
        button.addActionListener(this);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE && hasStarted) {
            pause_count++;
            testQuestionFrame.setEnabled(true);
            if (pause_count % 2 != 0) {
                go = false;
                testQuestionFrame.setEnabled(false);
                return;
            }
            fill_bar = new Thread(() -> fill(bar.getValue()));
            fill_bar.start();
        } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            //Ο χρόνος σταματάει μέχρι να κλείσει το παράθυρο
            go = false;
            SwingUtilities.invokeLater(Options::new);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    protected static void updateBar(int time) {
        fill_bar = new Thread(() -> fill(bar.getValue() + time));
        fill_bar.start();
    }

    protected static void closeFrame(){
        go=false;
        frame.dispose();
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == start) {
            bar.setVisible(true);
            Thread fill_bar = new Thread(() -> fill(time));
            fill_bar.start();
            start.setEnabled(false);
            hasStarted = true;
            testQuestionFrame.setEnabled(true);
        } else if (e.getSource() == testQuestionFrame) {
            //Ο χρόνος σταματάει μέχρι να απαντηθεί η ερώτηση
            go = false;
            SwingUtilities.invokeLater(() -> {
                try {
                    new Quiz();
                }catch (FileNotFoundException e1){
                    e1.printStackTrace();
                }
            });
        }

    }
}