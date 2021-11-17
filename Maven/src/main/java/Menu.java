import java.awt.event.*;
import javax.swing.*;
public class Menu {
    static class AlertAction implements ActionListener {
        private JFrame parent;

        AlertAction(JFrame parent) {
            this.parent = parent;
        }

        @Override public void actionPerformed(ActionEvent e) {
            JOptionPane.showMessageDialog(parent, "information", "Button Pressed!!", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    public static void main(String[] args) {
        JFrame f=new JFrame("Menu");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        final JTextField tf=new JTextField();
        tf.setBounds(50,50, 150,20);
        JButton b1=new JButton("Start Game");
        b1.setBounds(150,150,150,30);
        b1.addActionListener(new AlertAction(f));
        f.add(b1);f.add(tf);
        JButton b2=new JButton("Login");

        f.add(b2);
        b2.setBounds(150,250,100,30);
        f.setSize(400,400);
        f.setLayout(null);
        f.setVisible(true);

    }
}