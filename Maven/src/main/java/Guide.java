import javax.swing.*;

/**
 * Κλάση για περιγραφή οδηγιών
 *
 * @author Panagiotis Spanakis kai synergates
 */

public class Guide {

    JFrame frame;
    //-------test changes------//
    JLabel label = new JLabel();
    //-------test changes end------//

    public Guide() {
        // Εξατομίκευση παραθύρου
        frame = new JFrame(); //create frame
        frame.setTitle("Guide"); //setTitle of frame
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.setResizable(false);
        frame.setSize(600, 600);
        frame.setVisible(true);
        frame.setLayout(null);
        frame.setIconImage(Main.icon.getImage());
        //-------test changes------//
        label.setIcon(Main.background);
        label.setBounds(0,0,1000,1000);
        frame.add(label);
        //Για να εμφανίζεται στο κέντρο της οθόνης του χρήστη
        frame.setLocationRelativeTo(null);
        //-------test changes end------//

    }

}
