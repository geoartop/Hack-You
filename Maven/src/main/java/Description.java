import javax.swing.*;
import java.awt.*;

/**
 * Κλάση που περιγράφει το παιχνίδι και τη "πλοκή"
 *
 * @author Team Hack-You
 */
public class Description {

    JFrame frame;
    //-------test changes------//
    JLabel label = new JLabel();
    //-------test changes end------//

    public Description() {
        frame = new JFrame(); //create frame
        frame.setTitle("Game description"); //setTitle of frame
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.setResizable(false);
        frame.setSize(600, 600);
        frame.setVisible(true);
        frame.setLayout(null);
        frame.setIconImage(Main.icon.getImage());
        frame.setLocationRelativeTo(null);
        //-------test changes------//
        //Set Scaled Background
        Image img = Main.background.getImage();
        Image temp = img.getScaledInstance(585, 600, Image.SCALE_SMOOTH);
        ImageIcon back = new ImageIcon(temp);
        label.setIcon(back);
        label.setBounds(0, 0, 600, 600);
        frame.add(label);
        //Για να εμφανίζεται στο κέντρο της οθόνης του χρήστη

        //-------test changes end------//
    }
}
