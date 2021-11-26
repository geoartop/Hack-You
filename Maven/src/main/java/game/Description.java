package game;
import javax.swing.*;
import java.awt.*;

/**
 * Κλάση που περιγράφει το παιχνίδι και τη "πλοκή"
 *
 * @author Team Hack-You
 */
public class Description{

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
        FrameSetter.scaleBackground(label,600,600);
        frame.add(label);

        //-------test changes end------//
    }
}