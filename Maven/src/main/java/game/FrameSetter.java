package game;

import javax.swing.*;

public class FrameSetter {

    public static void setFrame(JFrame frame,String title,int width,int height){
        frame.setTitle(title); //setTitle of frame
        frame.setResizable(false);
        frame.setSize(width, height);
        frame.setVisible(true);
        frame.setLayout(null);
        frame.setIconImage(Main.icon.getImage());
        frame.setLocationRelativeTo(null);
    }
}
