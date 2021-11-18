import javax.swing.*;

/**
 * Κλάση για περιγραφή οδηγιών
 *
 * @author Panagiotis Spanakis kai synergates
 */

public class Guide {

    JFrame frame;
    private ImageIcon Icon = new ImageIcon("src/main/resources/maze-icon.png");
    public Guide() {
        // Εξατομίκευση παραθύρου
        frame = new JFrame(); //create frame
        frame.setTitle("Guide"); //setTitle of frame
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.setResizable(false);
        frame.setSize(600, 600);
        frame.setVisible(true);
        frame.setLayout(null);
        frame.setIconImage(Icon.getImage());
    }

}
