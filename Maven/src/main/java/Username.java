import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Κλάση για εισαγωγή username χρήστη και έλεγχος εγκυρότητας
 *
 * @author Panagiotis Spanakis
 */

public class Username implements ActionListener, KeyListener {

    private JFrame frame;
    private JButton submit = new JButton("Submit");
    private JTextField textField = new JTextField();
    /**
     * Θέλουμε να γνωρίζουν όλες οι κλάσεις το username του παίκτη ώστε να μπορεί να αποθηκευτεί πιο εύκολα
     */
    protected static String username;

    public Username() {
        // Εξατομίκευση παραθύρου
        frame = new JFrame();
        frame.setTitle("Set Username");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setSize(300, 300);
        frame.setVisible(true);
        frame.setLayout(null);
        frame.getContentPane().setBackground(Color.black);

        submit.setBounds(100, 200, 100, 30);
        submit.setHorizontalAlignment(JButton.CENTER);
        submit.setFocusable(false);
        submit.addActionListener(this);

        textField.setBounds(75, 100, 150, 50);
        textField.setPreferredSize(new Dimension(200, 50));
        //Key Bind
        submit.addKeyListener(this);

        frame.add(submit);
        frame.add(textField);
        frame.getRootPane().setDefaultButton(submit);

    }

    /**
     * Ενέργεια όταν κάνουμε κλικ στο κουμπί
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submit) {
            username = textField.getText();
            if (username.equals("")) {
                JOptionPane.showMessageDialog(null, "You must enter your username!", "Reminder", JOptionPane.ERROR_MESSAGE);
                return;
            } else if (username.equals("spanakis")) {
                JOptionPane.showMessageDialog(null, "Congrats you won already everything!", "Reminder", JOptionPane.INFORMATION_MESSAGE);
                System.exit(1);
            }
            new Menu();
        }
        frame.dispose();
    }

    //Απλά κουβαλιέται λόγω του implementation
    @Override
    public void keyTyped(KeyEvent e) {
    }

    /**
     * Ενέργεια για όταν πατιέται το κουμπί enter
     */
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            username = textField.getText();
            if (username.equals("")) {
                JOptionPane.showMessageDialog(null, "You must enter your username!", "Reminder", JOptionPane.ERROR_MESSAGE);
                return;
            }
            JOptionPane.showMessageDialog(null, "You've Submitted the name " + username, "Info", JOptionPane.ERROR_MESSAGE);
            new Menu();

        }
        frame.dispose();

    }

    //Απλά κουβαλιέται λόγω του implementation
    @Override
    public void keyReleased(KeyEvent e) {
    }
}

