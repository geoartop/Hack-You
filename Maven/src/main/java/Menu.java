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
    JButton description=new JButton("Game Description");
    JLabel label = new JLabel();
    ImageIcon Icon = new ImageIcon("src/main/resources/maze-icon.png");

    public Menu() {
        // Εξατομίκευση παραθύρου
        frame = new JFrame(); //create frame
        frame.setTitle("Menu"); //setTitle of frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setSize(600, 600);
        frame.setVisible(true);
        frame.setLayout(null);
        frame.setIconImage(Icon.getImage());

        setButton(start,Y);
        setButton(how2play,Y+100);
        setButton(credits,Y+200);
        setButton(description,Y+300);

        label.setText(Username.username);
        label.setBounds(0, 0, WIDTH, HEIGHT);

        frame.add(start);
        frame.add(how2play);
        frame.add(credits);
        frame.add(description);
        frame.add(label);
    }

    /**
     * Μέθοδος δημιουργίας Κουμπιών
     */
    public void setButton(JButton button,int y) {
        button.setBounds(X, y, WIDTH, HEIGHT);
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
        } else if(e.getSource()==credits){
            new Credits();
        }else {
            new Description();
        }
    }
}