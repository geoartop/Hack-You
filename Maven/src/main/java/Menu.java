import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class Menu implements ActionListener{

    JFrame frame;
    JButton start=new JButton("Start Game");
    JButton how2play=new JButton("How to Play");
    JButton credits=new JButton("Show Credits");
    JLabel label=new JLabel();

    public Menu() {
        frame=new JFrame(); //create frame
        frame.setTitle("Menu"); //setTitle of frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setSize(600,600);
        frame.setVisible(true);
        frame.setLayout(null);

        start.setBounds(250,200,150,50);
        start.setFocusable(false);
        start.addActionListener(this);

        how2play.setBounds(250,300,150,50);
        how2play.setFocusable(false);
        how2play.addActionListener(this);

        credits.setBounds(250,400,150,50);
        credits.setFocusable(false);
        credits.addActionListener(this);

        label.setText(Username.username);
        label.setBounds(0,0,150,50);

        frame.add(start);
        frame.add(how2play);
        frame.add(credits);
        frame.add(label);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==start){
            frame.dispose();
            new Levels();
        }else if(e.getSource()==how2play){
            frame.dispose();
            new Guide();
        }else {
            frame.dispose();
            new Credits();
        }
    }
}