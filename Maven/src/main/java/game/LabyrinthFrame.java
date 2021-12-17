package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

/**
 * Παράθυρο όπου τρέχει ο λαβύρινθος
 *
 * @author Team Hack-You
 */
public class LabyrinthFrame implements ActionListener {

    private JFrame frame;
    private JProgressBar bar;
    private GamePanel gamePanel = new GamePanel(this);

    private JButton start = new JButton("start");

    //Μεταβλητές χρήσιμες για τη λειτουργία του progressBar
    private boolean go = true; // Για το αν συνεχίζει το progressBar ή βρίσκεται σε pause
    protected boolean hasStarted = false; // Για το αν έχει αρχίσει το παιχνίδι

    //Μεταβλητές για πόσο χρόνο ο παίκτης θα κερδίζει χάνει ανάλογα με την απάντησή του στις ερωτήσεις
    protected static int for_correct;
    protected static int for_wrong;

    //Πόσο χρόνο σε seconds θα έχει ο παίκτης
    private static int time;

    // Αν ο παίκτης έχει χάσει ή όχι
    protected boolean hasLost = false;

    //Thread το οποίο τρέχει τη "φόρτωση" του εναπομένοντος χρόνου του παίκτη
    private Thread fill_bar;
    //Δευτερόλεπτα που απομένουν στον παίκτη για να δραπετεύσει από τον λαβύρινθο
    private int counter;

    //--------------------------------------------------------------------------------------//

    /**
     * Αρχικοποίηση μεταβλητών για χρόνο παιχνιδιού και win/loss χρόνου ανάλογα με την απάντηση στις ερωτήσεις
     */
    protected static void setLabyrinth() {
        switch (Levels.difficulty) {
            case "Easy":
                time = 100;
                for_correct = 5;
                for_wrong = -2;
                break;
            case "Medium":
                time = 75;
                for_correct = 5;
                for_wrong = -5;
                break;
            default:
                time = 10;
                for_correct = 3;
                for_wrong = -5;
                break;
        }

    }

    /**
     * Δημιουργία frame
     */
    private void createFrame() {
        frame = new JFrame();
        frame.setTitle("Labyrinth"); //setTitle of frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setSize(780, 660);
        frame.setVisible(true);
        frame.setLayout(new BorderLayout());
        frame.setIconImage(Main.icon.getImage());
        //Για να εμφανίζεται στο κέντρο της οθόνης του χρήστη
        frame.setLocationRelativeTo(null);
    }

    /**
     * Δημιουργία progressBar
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

    public LabyrinthFrame() {
        if (!Menu.music.clip.isActive() && ButtonSetter.playSound)
            Menu.playMusic();

        createFrame();
        createBar();

        /*Για να μην υπάρχει καμία περίπτωση να κολλήσει
        η κίνηση του παίκτη σε περίπτωση μετακίνησης του παραθύρου*/
        frame.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentMoved(ComponentEvent e) {
                super.componentMoved(e);
                gamePanel.player.stabilizePlayer();
            }
        });

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
            if (counter == 0)
                break;
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

    /**
     * Ανανέωση του χρόνου του progressBar χωρίς την παύση λειτουργίας του
     *
     * @param time : ο χρόνος που προσθαφαιρείται από το χρόνο που απομένει
     */
    protected void editBarTime(int time) {
        counter += time;
        bar.setString(String.format("%d seconds left", counter));
        bar.setValue(counter);
    }

    private void setButton(JButton button, int y) {
        button.setBounds(250, y, 100, 50);
        button.setFocusable(false);
        button.setHorizontalAlignment(JButton.CENTER);
        button.setFont(new Font("Calibri", Font.ITALIC, 20));
        button.addActionListener(this);
        start.setVisible(true);
    }

    /**
     * Μέθοδος ανανέωσης progressBar
     *
     * @param time : ο χρόνος που προσθαφαιρείται από το χρόνο που απομένει
     */
    protected void updateBar(int time) {
        fill_bar = new Thread(() -> fill(bar.getValue() + time));
        fill_bar.start();
    }

    /**
     * Μέθοδος κλεισίματος παραθύρου παιχνιδιού (διακοπή παιχνιδιού)
     */
    protected void closeFrame() {
        hasStarted = false;
        frame.dispose();
    }

    /**
     * Μέθοδος τερματισμού παιχνιδιού
     *
     * @param hasWon : true σε περίπτωση νίκης, false σε περίπτωση αποτυχίας
     */
    protected void closeFrame(boolean hasWon) {
        Menu.stopMusic();
        hasStarted = false;
        if (hasWon) {
            stopBar();
            SwingUtilities.invokeLater(WinFrame::new);
        } else {
            SwingUtilities.invokeLater(DeathFrame::new);
        }
        frame.dispose();

    }

    /**
     * Παύση progressBar
     */
    protected void stopBar() {
        go = false;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == start) {
            //Εμφάνιση progressBar και έναρξη countdown
            bar.setVisible(true);
            Thread fill_bar = new Thread(() -> fill(time));
            fill_bar.start();
            start.setEnabled(false);
            hasStarted = true;
            start.setVisible(false);
            //Για να μπορεί ο παίκτης να αρχίσει να κινείται
            gamePanel.gameState = gamePanel.playState;
        }

    }
}