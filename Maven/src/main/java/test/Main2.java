package test;

import javax.swing.*;

public class Main2 {
    public static void main(String[] args) {
        JFrame frame=new JFrame("game");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(false);

        GamePanel gamePanel=new GamePanel();
        frame.add(gamePanel);

        frame.pack();

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
