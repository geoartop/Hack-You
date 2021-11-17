// MyLogin.java

import javax.swing.*;
import java.awt.event.*;

public class Username {

    public Username() {
        JFrame f = new JFrame("Login");
        JButton bok = new JButton("OK");
        bok.setBounds(150,200,100,30);
        f.add(bok);
        f.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        bok.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                f.dispose();
                //new SecondFrame();//
            }
        });
        f.setSize(600,600);
        f.setVisible(true);
        f.setLayout(null);
    }

    public static void main(String[] args) {
        new Username();
    }
}

