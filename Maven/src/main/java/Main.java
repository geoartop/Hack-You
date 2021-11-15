import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 *
 * @author Panagiotis Spanakis k synergates
 */
public class Main {
    class AlertAction implements ActionListener {
        private JFrame parent;

        AlertAction(JFrame parent) {
            this.parent = parent;
        }

        @Override public void actionPerformed(ActionEvent e) {
            JOptionPane.showMessageDialog(parent, "information", "Kali phimosi!!", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public Main() {
        JFrame jf = new JFrame("Hack-You");
        JButton jb = new JButton("Click Me!");

        jf.setBounds(0, 0, 800, 600);

        jf.setLayout(new BorderLayout());
        jf.add(jb, BorderLayout.CENTER);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jb.addActionListener(new AlertAction(jf));

        // Remember, the method show() is deprecated
        jf.setVisible(true);
    }

    public static void main(String[] args) {
        new Main();
    }
}
