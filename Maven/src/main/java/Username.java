import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;

/**
 * Κλάση για εισαγωγή username χρήστη και έλεγχος εγκυρότητας
 *
 * @author Panagiotis Spanakis
 */

public class Username implements ActionListener{

    private JFrame frame;
    private JButton submit = new JButton("Submit");
    private JTextField textField = new JTextField();
    //-------test changes------//
    JLabel label = new JLabel();

    //-------test changes end------//
    ImageIcon icon2;

    /**
     * Θέλουμε να γνωρίζουν όλες οι κλάσεις το username του παίκτη ώστε να μπορεί να αποθηκευτεί πιο εύκολα
     */
    protected static String username;

    public Username() {
        // Εξατομίκευση παραθύρου
        frame = new JFrame();
        frame.setTitle("Set Username");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Για να μη μεγαλώνει/μικραίνει το μέγεθος του παραθύρου
        frame.setResizable(false);
        frame.setSize(1600, 1000);
        frame.setVisible(true);
        frame.setLayout(null);
        frame.getContentPane().setBackground(Color.black);
        frame.setIconImage(Main.icon.getImage());


        icon2= new ImageIcon("src/main/resources/buttons1234.jpg");
        submit.setIcon(icon2);
        submit.setHorizontalTextPosition(JButton.CENTER);
        submit.setFont(new Font("Calibri",Font.BOLD,16));
        submit.setForeground(Color.black);
        submit.setBounds(755, 470, 100, 30);
        //δεν κανουν τιποτα
        //submit.setHorizontalAlignment(JButton.CENTER);
        //submit.setVerticalAlignment(JButton.CENTER);
        submit.setFocusable(false);



        submit.addActionListener(this);

        textField.setBounds(650, 400, 300, 50);
        textField.setPreferredSize(new Dimension(300, 50));
        //Key Bind
        //submit.addKeyListener(this);

        frame.add(submit);
        frame.add(textField);
        // Για να λειτουργεί το κουμπί enter
        frame.getRootPane().setDefaultButton(submit);
        //-------test changes------//
        label.setIcon(Main.background);
        label.setBounds(0,0,1600,1000);
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

