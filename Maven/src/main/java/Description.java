import javax.swing.ImageIcon;
import javax.swing.JFrame;

/**
 * Κλάση που περιγράφει το παιχνίδι και την "πλοκή"
 *
 * @author Panagiotis Spanakis kai synergates
 */
public class Description {

    JFrame frame;
    private ImageIcon Icon = new ImageIcon("src/main/resources/maze-icon.png");

    public Description(){
        frame = new JFrame(); //create frame
        frame.setTitle("Game description"); //setTitle of frame
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.setResizable(false);
        frame.setSize(600, 600);
        frame.setVisible(true);
        frame.setLayout(null);
        frame.setIconImage(Icon.getImage());
    }
}
