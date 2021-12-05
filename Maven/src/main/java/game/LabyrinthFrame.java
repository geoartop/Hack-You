package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Team Hack-You
 * <p> Κλάση όπου θα τρέχει ο λαβύρινθος
 */
public class LabyrinthFrame implements ActionListener {

    //Δηλώνω static το frame έτσι ώστε να μπορεί να ανανεώνεται από τα Options
    protected static JFrame frame;
    private static JProgressBar bar;
    private GamePanel gamePanel = new GamePanel();

    JButton start = new JButton("start");

    //Μεταβλητές χρήσιμες για τη λειτουργία του progressBar
    private static boolean go = true; // Για το αν συνεχίζει το παιχνίδι ή βρίσκεται σε pause
    protected static boolean hasStarted = false; // Για το αν έχει αρχίσει το παιχνίδι

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
                time = 200;
                for_correct = 5;
                for_wrong = -2;
                break;
            case "Medium":
                time = 150;
                for_correct = 5;
                for_wrong = -3;
                break;
            default:
                time = 10;
                for_correct = 3;
                for_wrong = -5;
                break;
        }

    }

    private void createFrame() {
        frame = new JFrame();
        frame.setTitle("Labyrinth"); //setTitle of frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setSize(780, 680);
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
        bar.setPreferredSize(new Dimension(600, 50));
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

        frame.add(bar, BorderLayout.NORTH);
        frame.add(start, BorderLayout.SOUTH);

        frame.add(gamePanel, BorderLayout.CENTER);
        gamePanel.setupGame();

    }


    /**
     * Μέθοδος λειτουργίας progressBar
     * @param flg : ο χρόνος που θα έχει ο παίκτης
     */
    private static void fill(int flg) {
        int counter = flg;

        while (counter > 0) {
            if (!go) {
                go = true;
                return;
            }
            bar.setString(String.format("%d seconds left", counter));
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
        closeFrame();
    }

    private void setButton(JButton button, int y) {
        button.setBounds(250, y, 100, 50);
        button.setFocusable(false);
        button.setHorizontalAlignment(JButton.CENTER);
        button.setFont(new Font("Calibri", Font.ITALIC, 20));
        button.addActionListener(this);
    }

    /**
     * Μέθοδος ανανέωσης progressBar
     * @param time : ο χρόνος που προσθαφαιρείται από το χρόνο που απομένει
     */
    protected static void updateBar(int time) {
        fill_bar = new Thread(() -> fill(bar.getValue() + time));
        fill_bar.start();
    }

    /**
     * Μέθοδος τερματισμού παιχνιδιού
     */
    protected static void closeFrame() {
        hasStarted = false;
        frame.dispose();
    }

    /**
     * Μέθοδος παύσης progressBar
     */
    protected static void stopBar() {
        go = false;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == start) {
            bar.setVisible(true);
            Thread fill_bar = new Thread(() -> fill(time));
            fill_bar.start();
            start.setEnabled(false);
            hasStarted = true;
            //Για να μπορεί ο παίκτης να αρχίσει να κινείται
            gamePanel.gameState = gamePanel.playState;
        }

    }
}