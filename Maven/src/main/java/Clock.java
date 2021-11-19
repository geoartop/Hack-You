/**
 * @author Panagiotis Spanakis kai synergates
 * Λειτουργική Κλάση για το progressBar
 */

import javax.swing.*;


public class Clock {

    JFrame frame;
    JProgressBar bar=new JProgressBar();

    public Clock(){
        frame = new JFrame(); //create frame
        frame.setTitle("Guide"); //setTitle of frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setSize(600, 600);
        frame.setVisible(true);
        frame.setLayout(null);
        frame.setIconImage(Main.icon.getImage());// hope it works


    }

}
