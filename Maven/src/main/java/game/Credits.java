package game;
import javax.swing.*;
import java.awt.*;

/**
 * Κλάση για παρουσίαση μελών και ρόλων αυτών
 *
 * @author Team Hack-You
 */

/*
 * Ιδέα να φορτώνουν τα credits όπως στις ταινίες
 */
public class Credits {

    JFrame frame;
    //-------test changes------//
    JLabel label = new JLabel(); //create label
    //-------test changes end------//

    public Credits() {
        // Εξατομίκευση παραθύρου
        frame = new JFrame(); //create frame
        frame.setTitle("Credits"); //setTitle of frame
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.setResizable(false);
        frame.setSize(600, 600);
        frame.setVisible(true);
        frame.setLayout(null);
        frame.setIconImage(Main.icon.getImage());
        frame.setLocationRelativeTo(null);
        //-------test changes------//

        //Set Scaled Background
        Image img=Main.background.getImage();
        Image temp=img.getScaledInstance(585,600,Image.SCALE_SMOOTH);
        ImageIcon back=new ImageIcon(temp);
        label.setIcon(back);
        label.setBounds(0,0,600,600);
        //Για να εμφανίζεται στο κέντρο της οθόνης του χρήστη
        frame.add(label);


        //-------test changes end------//
    }
}