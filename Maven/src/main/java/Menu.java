import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/** Κλάση για StartMenu
 *
 * @author Panagiotis Spanakis kai synergates
 *
 */

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

        setButton(start,250,200,150,50);
        setButton(how2play,250,300,150,50);
        setButton(credits,250,400,150,50);

        label.setText(Username.username);
        label.setBounds(0,0,150,50);

        frame.add(start);
        frame.add(how2play);
        frame.add(credits);
        frame.add(label);
    }

    /** Μέθοδος δημιουργίας Κουμπιών*/
    public void setButton(JButton button,int x,int y,int width,int height){
        button.setBounds(x,y,width,height);
        button.setFocusable(false);
        button.addActionListener(this);
    }

    /**Ενέργεια όταν κάνουμε κλικ στο κουμπί*/
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==start){
            new Levels();
            frame.dispose();
        }else if(e.getSource()==how2play){
            new Guide();
        }else {
            new Credits();
        }
    }
}