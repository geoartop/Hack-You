package game;

import highscoreTest.HighScore;
import highscoreTest.HighScoreFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Παράθυρο που ανοίγει στην περίπτωση που ο παίκτης κέρδισε
 *
 * @author Team Hack-You
 */
public class WinFrame implements ActionListener {

    JFrame frame;
    JLabel backgroundLabel = new JLabel();
    JButton playAgain = new JButton("play again");
    public JButton seeHighScores = new JButton("check HighScore table");
    JButton back_to_menu = new JButton("back to Menu");
    JButton exit = new JButton("exit");
    HighScoreFrame highScoreFrame;


    public WinFrame() {
        frame = new JFrame();
        FrameSetter.setFrame(frame, "Victory", 800, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Έλεγχος για το αν ο παίκτης έκανε νέο highscore
        new HighScore(Username.getUsername(), calculateScore());

        ButtonSetter.setButton(playAgain, 275, 300, 250, 50, "Calibri", 20, this, 2);
        ButtonSetter.setButton(seeHighScores, 275, 400, 250, 50, "Calibri", 20, this, 2);
        ButtonSetter.setButton(back_to_menu, 275, 500, 250, 50, "Calibri", 20, this, 2);
        ButtonSetter.setButton(exit, 275, 600, 250, 50, "Calibri", 20, this, 2);

        frame.add(playAgain);
        frame.add(seeHighScores);
        frame.add(back_to_menu);
        frame.add(exit);

        GraphicPane graphicPane = new GraphicPane("VICTORY!",800,100,new Color(136, 201, 87),60,1);
        graphicPane.setBounds(0,50,800,150);
        frame.add(graphicPane);

        FrameSetter.scaleBackground(backgroundLabel, 800, 800);
        frame.add(backgroundLabel);
    }

    //TODO(all) προσθήκη συνάρτησης υπολογισμού score
    public int calculateScore() {

        return 0;
    }

    public void check() {
        if (highScoreFrame == null)
            return;
        if (highScoreFrame.getIsOpen())
            highScoreFrame.closeFrame();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ButtonSetter.playSE();
        if (e.getSource() == playAgain) {
            check();
            SwingUtilities.invokeLater(LabyrinthFrame::new);
        } else if (e.getSource() == seeHighScores) {
            highScoreFrame = new HighScoreFrame(this);
            seeHighScores.setEnabled(false);
            return;
        } else if (e.getSource() == back_to_menu) {
            check();
            SwingUtilities.invokeLater(Menu::new);
        } else {
            System.exit(1);
        }
        frame.dispose();
    }
}
