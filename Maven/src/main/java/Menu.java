import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Κλάση για StartMenu
 *
 * @author Panagiotis Spanakis kai synergates
 */

public class Menu extends JFrame implements ActionListener {

    /**Initialize μεταβλητών διαστάσεων*/
    private final int X=380;
    private final int Y=200;
    private final int WIDTH=200;
    private final int HEIGHT=50;
    private int counter = 0;
    ImageIcon icon3;
    JButton start = new JButton("Start Game");
    JButton how2play = new JButton("How to Play");
    JButton credits = new JButton("Show Credits");
    JButton description=new JButton("Game Description");
    JLabel label = new JLabel();
    //-------test changes------//
    JLabel label2 = new JLabel();
    //-------test changes end------//

    public Menu() {
        // Εξατομίκευση παραθύρου
        this.setTitle("Menu"); //setTitle of frame
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(970, 850);
        this.setVisible(true);
        this.setLayout(null);
        this.setIconImage(Main.icon.getImage());

        setButton(start,Y);
        setButton(how2play,Y+100);
        setButton(credits,Y+200);
        setButton(description,Y+300);


        this.add(start);
        this.add(how2play);
        this.add(credits);
        this.add(description);
        this.add(label);
        //-------test changes------//
        label2.setIcon(Main.background);
        label2.setBounds(0,0,1000,1000);
        this.add(label2);
        //-------test changes end------//
    }

    /**
     * Μέθοδος δημιουργίας Κουμπιών
     */
    public void setButton(JButton button,int y) {
        counter++;
        button.setBounds(X, y, WIDTH, HEIGHT);
        button.setFocusable(false);
        if (counter % 2 == 1) {
            icon3 = new ImageIcon("src/main/resources/wood2.png");
        } else {
            icon3 = new ImageIcon("src/main/resources/wood1.png");
        }
        button.setIcon(icon3);
        button.setHorizontalTextPosition(JButton.CENTER);
        button.setFont(new Font("Calibri",Font.BOLD,25));
        button.setForeground(Color.black);
        //button.setHorizontalAlignment(JButton.CENTER);
        button.addActionListener(this);
        //button.setFont(new Font("Calibri",Font.ITALIC,16));
    }

    /**
     * Ενέργεια όταν κάνουμε κλικ στο κουμπί
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == start) {
            new Levels();
            this.dispose();
        } else if (e.getSource() == how2play) {
            new Guide();
        } else if(e.getSource()==credits){
            new Credits();
        }else {
            new Description();
        }
    }
}