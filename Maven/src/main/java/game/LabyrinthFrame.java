package game;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JProgressBar;
import javax.swing.SwingUtilities;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

/**
 * <p>Παράθυρο όπου τρέχει ο λαβύρινθος</p>
 *
 * @author Team Hack-You
 * @version 1.0
 */
public final class LabyrinthFrame implements ActionListener {

    private JFrame frame;
    private JProgressBar bar;
    private final GamePanel gamePanel = new GamePanel(this);

    private final JButton start = new JButton("start");

    //Μεταβλητές χρήσιμες για τη λειτουργία του progressBar
    private boolean go = true; // Αν συνεχίζει το progressBar ή βρίσκεται σε pause
    private boolean hasStarted = false; // Αν έχει αρχίσει το παιχνίδι

    //Μεταβλητές για πόσο χρόνο ο παίκτης θα κερδίζει χάνει ανάλογα με την απάντησή του στις ερωτήσεις
    static int for_correct;
    static int for_wrong;

    //Πόσο χρόνο σε seconds θα έχει ο παίκτης
    private static int time;

    // Αν ο παίκτης έχει χάσει ή όχι
    private boolean hasLost = false;

    //Δευτερόλεπτα που απομένουν στον παίκτη για να δραπετεύσει από τον λαβύρινθο
    private int counter;

    //Εάν έχει προηγηθεί restart
    private static boolean restartStatus = false;

    /**
     * <p>Getter for the field <code>restartStatus</code>.</p>
     *
     * @return a boolean
     */
    public static boolean getRestartStatus() {
        return restartStatus;
    }

    /**
     * <p>Setter for the field <code>restartStatus</code>.</p>
     *
     * @param restartStatus a boolean
     */
    public static void setRestartStatus(boolean restartStatus) {
        LabyrinthFrame.restartStatus = restartStatus;
    }

    /**
     * <p>Getter for the field <code>hasLost</code>.</p>
     *
     * @return a boolean
     */
    public boolean getHasLost() {
        return hasLost;
    }

    /**
     * <p>Getter for the field <code>hasStarted</code>.</p>
     *
     * @return a boolean
     */
    public boolean getHasStarted() {
        return hasStarted;
    }

    /**
     * <p>Αρχικοποίηση μεταβλητών για χρόνο παιχνιδιού <br>
     * και win/loss χρόνου ανάλογα με την απάντηση στις ερωτήσεις</p>
     */
    static void setLabyrinth() {
        switch (Levels.getDifficulty()) {
            case "Easy":
                time = 55;
                for_correct = 3;
                for_wrong = -5;
                break;
            case "Medium":
                time = 65;
                for_correct = 5;
                for_wrong = -5;
                break;
            default:
                time = 75;
                for_correct = 5;
                for_wrong = -5;
                break;
        }
    }

    /**
     * <p>Δημιουργία frame</p>
     */
    private void createFrame() {
        frame = new JFrame();
        FrameSetter.setFrame(frame, "Labyrinth", 780, 660);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
    }

    /**
     * <p>Δημιουργία progressBar</p>
     */
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

    /**
     * <p>Constructor for LabyrinthFrame.</p>
     */
    public LabyrinthFrame() {
        restartStatus = false;
        if (Menu.checkMusic()) {
            Menu.playMusic();
        }

        createFrame();
        createBar();

        /*Για να μην υπάρχει καμία περίπτωση να κολλήσει
        η κίνηση του παίκτη σε περίπτωση μετακίνησης του παραθύρου*/
        frame.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentMoved(ComponentEvent e) {
                super.componentMoved(e);
                gamePanel.playerStabilize();
            }
        });

        setButton(start);
        start.setBackground(Color.green);
        start.setFont(new Font("Calibri", Font.ITALIC, 25));

        frame.add(bar, BorderLayout.NORTH);
        frame.add(start, BorderLayout.SOUTH);

        frame.getRootPane().setDefaultButton(start);
        frame.add(gamePanel, BorderLayout.CENTER);
        gamePanel.setupGame();

    }

    /**
     * <p>Λειτουργία του progressBar.</p>
     *
     * @param flg : ο χρόνος που θα έχει ο παίκτης
     */
    private void fill(int flg) {
        counter = flg;
        // counter >= 0 : Για να γίνεται το activation του quiz και με 1 second left
        while (counter >= 0) {
            if (!go) {
                go = true;
                return;
            }
            if (counter == 0) {
                break;
            }
            bar.setString(String.format("%d seconds left", counter));
            bar.setValue(counter);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            counter--;

        }
        bar.setValue(0);
        hasLost = true;
        bar.setString("Game Over");

    }

    private void setButton(JButton button) {
        ButtonSetter.setButton(button, 250, 500, 100, 50, new Font("Calibri", Font.ITALIC, 20), this);
        button.setIcon(null);
    }

    /**
     * <p>Ανανέωσης progressBar</p>
     *
     * @param flg : ο χρόνος που προσθαφαιρείται από το χρόνο που απομένει
     */
    void updateBar(int flg) {
        int new_time = Math.min(bar.getValue() + flg, time);
        //Thread το οποίο τρέχει τη "φόρτωση" του εναπομένοντος χρόνου του παίκτη
        Thread fill_bar = new Thread(() -> fill(new_time));
        fill_bar.start();
    }

    /**
     * <p>Κλείσιμο παραθύρου παιχνιδιού (διακοπή παιχνιδιού)</p>
     */
    void closeFrame() {
        //SOS! CRUCIAL for thread safety
        gamePanel.terminate();
        frame.dispose();
    }

    /**
     * <p>Τερματισμός παιχνιδιού</p>
     *
     * @param hasWon : true σε περίπτωση νίκης, false σε περίπτωση αποτυχίας
     */
    void closeFrame(boolean hasWon) {
        hasStarted = false;
        if (hasWon) {
            stopBar();
            SwingUtilities.invokeLater(() -> new WinFrame(counter));
        } else {
            SwingUtilities.invokeLater(DeathFrame::new);
        }
        gamePanel.terminate();
        frame.dispose();

    }

    /**
     * <p>Παύση progressBar</p>
     */
    void stopBar() {
        go = false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        ButtonSetter.playSE();
        if (e.getSource() == start) {
            //Εμφάνιση progressBar και έναρξη countdown
            bar.setVisible(true);
            Thread fill_bar = new Thread(() -> fill(time));
            fill_bar.start();
            start.setEnabled(false);
            hasStarted = true;
            start.setVisible(false);
            //Για να μπορεί ο παίκτης να αρχίσει να κινείται
            gamePanel.setGameState(GamePanel.playState);
        }

    }
}
