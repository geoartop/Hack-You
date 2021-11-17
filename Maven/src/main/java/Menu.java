import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Κλάση για StartMenu
 *
 * @author Panagiotis Spanakis kai synergates
 */

public class Menu implements ActionListener {

    /**Initialize μεταβλητών διαστάσεων*/
    private final int X=225;
    private final int Y=200;
    private final int WIDTH=150;
    private final int HEIGHT=50;

    JFrame frame;
    JButton start = new JButton("Start Game");
    JButton how2play = new JButton("How to Play");
    JButton credits = new JButton("Show Credits");
    JLabel label = new JLabel();

    public Menu() {
        // Εξατομίκευση παραθύρου
        frame = new JFrame(); //create frame
        frame.setTitle("Menu"); //setTitle of frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setSize(600, 600);
        frame.setVisible(true);
        frame.setLayout(null);

        setButton(start, X, Y, WIDTH, HEIGHT);
        setButton(how2play, X, Y+100, WIDTH, HEIGHT);
        setButton(credits, X, Y+200, WIDTH, HEIGHT);

        label.setText(Username.username);
        label.setBounds(0, 0, WIDTH, HEIGHT);

        frame.add(start);
        frame.add(how2play);
        frame.add(credits);
        frame.add(label);
    }

    /**
     * Μέθοδος δημιουργίας Κουμπιών
     */
    public void setButton(JButton button, int x, int y, int width, int height) {
        button.setBounds(x, y, width, height);
        button.setFocusable(false);
        button.setHorizontalAlignment(JButton.CENTER);
        button.addActionListener(this);

    }

    /**
     * Ενέργεια όταν κάνουμε κλικ στο κουμπί
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == start) {
            new Levels();
            frame.dispose();
        } else if (e.getSource() == how2play) {
            new Guide();
        } else {
            new Credits();
        }
    }
}