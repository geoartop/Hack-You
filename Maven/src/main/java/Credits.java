import javax.swing.*;

/**
 * Κλάση για παρουσίαση μελών και ρόλων αυτών
 *
 * @author Panagiotis Spanakis kai synergates
 */


public class Credits {

    JFrame frame;

    public Credits() {
        // Εξατομίκευση παραθύρου
        frame = new JFrame(); //create frame
        frame.setTitle("Credits"); //setTitle of frame
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.setResizable(false);
        frame.setSize(600, 600);
        frame.setVisible(true);
        frame.setLayout(null);
    }
}