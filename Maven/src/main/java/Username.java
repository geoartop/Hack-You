// MyLogin.java

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Username implements ActionListener{

    private JFrame frame;
    private JButton submit=new JButton("Submit");
    private JTextField textField=new JTextField();
    protected static String username;

    public Username() {
        frame=new JFrame();
        frame.setTitle("Set Username");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setSize(300,300);
        frame.setVisible(true);
        frame.setLayout(null);
        frame.getContentPane().setBackground(Color.black);

        submit.setBounds(100,200,100,30);
        submit.setHorizontalAlignment(JButton.CENTER);
        submit.setFocusable(false);
        submit.addActionListener(this);

        textField.setBounds(75,100,150,50);
        textField.setPreferredSize(new Dimension(200,50));

        frame.add(submit);
        frame.add(textField);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==submit){
            username=textField.getText();
            if(username.equals("")){
                JOptionPane.showMessageDialog(null,"You must enter your username!","Reminder",JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            frame.dispose();
            new Menu();
        }
    }
}

