package game;

import highscoreTest.HighScore;
import highscoreTest.HighScoreFrame;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * <p> Παράθυρο που ανοίγει στην περίπτωση που ο παίκτης κέρδισε </p>
 *
 * @author Team Hack-You
 * @version 1.0
 */
public final class WinFrame implements ActionListener {

    private final JFrame frame;
    private final JLabel backgroundLabel = new JLabel();
    private final JButton playAgain = new JButton("play again");
    private final JButton seeHighScores = new JButton("check HighScore table");
    private final JButton back_to_menu = new JButton("back to Menu");
    private final JButton exit = new JButton("exit");
    private HighScoreFrame highScoreFrame;
    private final JLabel minLabel = new JLabel();
    private final JLabel thLabel = new JLabel();

    private final HighScore highScore;

    private int y = 300;

    /**
     * <p>Setter for <code>seeHighScores</code> enabled status</p>
     *
     * @param status a boolean
     */
    public void setSeeHighScoresStatus(boolean status) {
        seeHighScores.setEnabled(status);
    }

    /**
     * <p>Getter for the field <code>highScore</code>.</p>
     *
     * @return a {@link highscoreTest.HighScore} object
     */
    public HighScore getHighScore() {
        return highScore;
    }

    /**
     * <p>Constructor for WinFrame.</p>
     *
     * @param time a int
     */
    public WinFrame(int time) {
        frame = new JFrame();
        FrameSetter.setFrame(frame, "Victory", 800, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Έλεγχος για το αν ο παίκτης έκανε νέο highscore
        highScore = new HighScore(Username.getUsername(), calculateScore(time));

        setButton(playAgain);
        setButton(seeHighScores);
        setButton(back_to_menu);
        setButton(exit);

        FrameSetter.scaleImgToLabel(minLabel, 350, 0, 120, 100, new ImageIcon("src/main/resources/minotaur/minotaurlose3.png"));
        frame.add(minLabel);
        FrameSetter.scaleImgToLabel(thLabel, 365, 100, 100, 70, new ImageIcon("src/main/resources/thiseas2/thiseasswind.png"));
        frame.add(thLabel);

        GraphicPane graphicPane = new GraphicPane("VICTORY!", 800, 100, new Color(23, 131, 59), new Font("Times new Roman", Font.BOLD, 60));
        graphicPane.setBounds(0, 150, 800, 150);
        frame.add(graphicPane);

        FrameSetter.scaleBackground(backgroundLabel, 800, 800);
        frame.add(backgroundLabel);
    }

    /**
     * <p>setButton.</p>
     *
     * @param button a {@link JButton} object
     */
    private void setButton(JButton button) {
        ButtonSetter.setButton(button, 275, y, 250, 50, Main.mainFont, this);
        frame.add(button);
        y += 100;
    }

    /**
     * <p>calculateScore.</p>
     *
     * @param time an int
     * @return an int
     */
    public int calculateScore(int time) {
        double multiplier;
        if (Levels.getDifficulty().equals("Easy")) {
            multiplier = 1;
        } else if (Levels.getDifficulty().equals("Medium")) {
            multiplier = 1.5;
        } else {
            multiplier = 2;
        }
        return (int) (time * multiplier * Quiz.getPercentage() + Player.getCoinsCollected());
    }

    /**
     * <p>check.</p>
     */
    public void check() {
        if (highScoreFrame == null) {
            return;
        }
        if (highScoreFrame.getIsOpen()) {
            highScoreFrame.closeFrame();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        ButtonSetter.playSE();
        if (e.getSource() == playAgain) {
            check();
            SwingUtilities.invokeLater(LabyrinthFrame::new);
            Quiz.clearIndexes();
        } else if (e.getSource() == seeHighScores) {
            SwingUtilities.invokeLater(() -> highScoreFrame = new HighScoreFrame(WinFrame.this));
            seeHighScores.setEnabled(false);
            return;
        } else if (e.getSource() == back_to_menu) {
            check();
            SwingUtilities.invokeLater(Menu::new);
            Quiz.clearIndexes();
        } else {
            System.exit(1);
        }
        frame.dispose();
    }
}
