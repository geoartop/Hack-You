import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;

/**
 * Κλάση για εισαγωγή username χρήστη και έλεγχος εγκυρότητας
 *
 * @author Team Hack-You
 */

public class Username implements ActionListener {

    /**
     * Θέλουμε να γνωρίζουν όλες οι κλάσεις το username του παίκτη ώστε να μπορεί να αποθηκευτεί πιο εύκολα
     */
    protected static String username;
    //-------test changes------//
    JLabel label = new JLabel();
    //-------test changes end------//
    ImageIcon icon2;
    private final JFrame frame;
    private final JButton submit = new JButton("Submit");
    private final JTextField textField = new JTextField();

    public Username() {
        // Εξατομίκευση παραθύρου
        frame = new JFrame();
        frame.setTitle("Set Username");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Για να μη μεγαλώνει/μικραίνει το μέγεθος του παραθύρου
        frame.setResizable(false);
        frame.setSize(970, 850);
        frame.setVisible(true);
        frame.setLayout(null);
        frame.getContentPane().setBackground(Color.black);
        frame.setIconImage(Main.icon.getImage());
        //Για να εμφανίζεται στο κέντρο της οθόνης του χρήστη
        frame.setLocationRelativeTo(null);


        icon2 = new ImageIcon("src/main/resources/wood1.png");
        submit.setIcon(icon2);
        submit.setHorizontalTextPosition(JButton.CENTER);
        submit.setFont(new Font("Calibri", Font.BOLD, 25));
        submit.setForeground(Color.black);
        submit.setBounds(400, 350, 150, 45);
        //δεν κανουν τιποτα
        //submit.setHorizontalAlignment(JButton.CENTER);
        //submit.setVerticalAlignment(JButton.CENTER);
        submit.setFocusable(false);
        submit.addActionListener(this);

        textField.setBounds(325, 280, 300, 50);
        textField.setPreferredSize(new Dimension(300, 50));
        textField.setFont(new Font("Calibri", Font.BOLD, 25));
        textField.setSelectedTextColor(Color.green);
        //Key Bind
        //submit.addKeyListener(this);

        frame.add(submit);
        frame.add(textField);
        // Για να λειτουργεί το κουμπί enter
        frame.getRootPane().setDefaultButton(submit);
        //-------test changes------//
        label.setIcon(Main.background);
        label.setBounds(0, 0, 970, 850);
        frame.add(label);
        //-------test changes end------//


    }

    /**
     * Ενέργεια όταν κάνουμε κλικ στο κουμπί ή όταν πατάμε enter
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submit) {
            username = textField.getText();
            if (username.equals("")) {
                JOptionPane.showMessageDialog(null, "You must enter your username!", "Reminder", JOptionPane.ERROR_MESSAGE);
                return;
            } else if (username.toLowerCase(Locale.ROOT).equals("spanakis")) {
                JOptionPane.showMessageDialog(null, "Congrats you won already on everything!", "Reminder", JOptionPane.INFORMATION_MESSAGE);
                System.exit(1);
            } else if (username.toLowerCase(Locale.ROOT).equals("artopoulos")) {
                JOptionPane.showMessageDialog(null, "You lost already, good paradise", "Announcement", JOptionPane.WARNING_MESSAGE);
                System.exit(1);
            }
            new Menu();
        }
        frame.dispose();
    }


}

