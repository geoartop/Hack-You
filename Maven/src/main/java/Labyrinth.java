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
 *
 * Κλάση όπου θα τρέχει ο λαβίρυνθος
 */
public class Labyrinth implements KeyListener, ActionListener {

    JFrame frame;
    //protected static File file;
    /**ProgressBar*/
    JProgressBar bar = new JProgressBar(0, 100);
    JButton start = new JButton("Start");
    JButton pause = new JButton("pause");
    JButton goOn = new JButton("continue");
    private boolean go = true;
    private int pause_count = 0;
    private boolean hasStarted=false;

    protected static void setLabyrinth() {
        switch (Levels.difficulty) {
            case "easy":
                //file=EasyLabyrinth.txt;
                break;
            case "medium":
                //file=MediumLabyrinth.txt;
                break;
            default:
                //file=HardLabyrinth.txt;
                break;
        }

    }

    /**
     * Μέθοδος λειτουργίας progressBar
     */
    public void fill(int flg) {
        int counter = flg;

        while (counter > 0) {
            if (!go) {
                go = true;
                return;
            }
            bar.setValue(counter);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "A problem has occurred", "Error", JOptionPane.ERROR_MESSAGE);
            }
            counter--;
        }
        bar.setString("Game Over");
    }


    /**
     * PROBLEM !
     * TODO FIX LAGGING PROGRESS BAR
     *
     * UPDATE -> FIXED Μέσα από Threading
     */
    public Labyrinth() {
        frame = new JFrame(); //create frame
        frame.setTitle("Labyrinth"); //setTitle of frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setSize(600, 600);
        frame.setVisible(true);
        frame.setLayout(null);
        frame.setIconImage(Main.icon.getImage());

        bar.setValue(100);
        bar.setBounds(0, 0, 600, 50);
        bar.setStringPainted(true);
        bar.setFont(new Font("Arial", Font.BOLD, 25));
        bar.setForeground(Color.red);
        bar.setBackground(Color.black);

        setButton(start, 300);
        setButton(pause, 400);
        setButton(goOn, 500);
        goOn.setEnabled(false);

        //Key Bind
        frame.addKeyListener(this);

        frame.add(bar);
        frame.add(start);
        frame.add(pause);
        frame.add(goOn);

    }

    public void setButton(JButton button, int y) {
        button.setBounds(300, y, 150, 50);
        button.setFocusable(false);
        button.addActionListener(this);
        button.setHorizontalAlignment(JButton.CENTER);
        button.setFont(new Font("Arial", Font.ITALIC, 20));
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

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == start) {
            Thread fill_bar = new Thread(() -> fill(100));
            fill_bar.start();
            //JOptionPane.showMessageDialog(null,"working!!","test",JOptionPane.INFORMATION_MESSAGE);
            start.setEnabled(false);
            goOn.setEnabled(true);
            hasStarted=true;
        } else if (e.getSource() == pause) {
            go = false;
        } else {
            Thread fill_bar2 = new Thread(() -> fill(bar.getValue()));
            fill_bar2.start();
        }
    }
}
