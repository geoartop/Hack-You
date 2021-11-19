import javax.swing.*;

/**
 * Κλάση για παρουσίαση μελών και ρόλων αυτών
 *
 * @author Panagiotis Spanakis kai synergates
 */

/**
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
        //-------test changes------//
        label.setIcon(Main.background); // set Icon for label
        label.setBounds(0,0,1000,1000); // set x,y position within frame and dimensions
        frame.add(label);
        //-------test changes end------//
    }
}
