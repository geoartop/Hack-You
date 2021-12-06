package game;

import highscoreTest.HighScore;
import highscoreTest.HighScoreFrame;
import sun.font.DelegatingShape;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Παράθυρο που ανοίγει στην περίπτωση που ο παίκτης κέρδισε
 *
 * @author Team Hack-You
 */
public class WinFrame implements ActionListener {

    JFrame frame;
    JLabel backgroundLabel;
    JButton playAgain = new JButton("try again");
    JButton seeHighScores = new JButton("check HighScore table");
    JButton back_to_menu = new JButton("back to Menu");
    JButton exit = new JButton("exit");


    public WinFrame() {
        frame = new JFrame();
        FrameSetter.setFrame(frame, "Victory", 600, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        new HighScore(Username.getUsername(), calculateScore());

        frame.add(playAgain);
        frame.add(seeHighScores);
        frame.add(back_to_menu);
        frame.add(exit);
        FrameSetter.scaleBackground(backgroundLabel, 600, 600);
        frame.add(backgroundLabel);
    }

    public int calculateScore() {

        return 0;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == playAgain) {
            SwingUtilities.invokeLater(LabyrinthFrame::new);
        } else if (e.getSource() == seeHighScores) {
            SwingUtilities.invokeLater(HighScoreFrame::new);
        } else if (e.getSource() == back_to_menu) {
            SwingUtilities.invokeLater(Menu::new);
        } else {
            System.exit(1);
        }
        frame.dispose();
    }
}
