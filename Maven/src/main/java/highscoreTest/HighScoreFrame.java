package highscoreTest;

import game.FrameSetter;

import javax.swing.*;
import java.awt.*;

public class HighScoreFrame {
    JFrame frame;
    JLabel backgroundLabel = new JLabel();
    JLabel[] labels = new JLabel[HighScore.playerInfo.size()];
    JLabel headLabel=new JLabel();

    public HighScoreFrame() {
        frame = new JFrame();
        FrameSetter.setFrame(frame, "HighScore Table", 800, 1200);
        FrameSetter.scaleBackground(backgroundLabel, 800, 1200);

        setLabels();

        headLabel.setBounds(275,100,500,50);
        headLabel.setForeground(Color.black);
        headLabel.setFont(new Font("Calibri", Font.BOLD, 35));
        headLabel.setText("Πίνακας Ηighscore");
        frame.add(headLabel);

        for (JLabel label : labels)
            frame.add(label);

        displayPlayerInfo();
        frame.add(backgroundLabel);
    }

    private void setLabels() {
        for (int i = 0; i < labels.length; i++) {
            labels[i] = new JLabel();
            labels[i].setBounds(800/2-100, (i + 3) * 50, 500, 30);
            labels[i].setBackground(new Color(50, 50, 50));
            labels[i].setForeground(new Color(5, 5, 5));
            labels[i].setFont(new Font("Calibri", Font.BOLD, 25));
        }
    }

    private void displayPlayerInfo() {
        for (int i = 0; i < labels.length; i++)
            labels[i].setText(String.format("%d) %s : %d", i + 1, HighScore.playerInfo.get(i).getName(), HighScore.playerInfo.get(i).getScore()));

    }
}
