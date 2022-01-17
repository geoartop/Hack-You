package game;

import highscoreTest.HighScore;
import highscoreTest.HighScoreFrame;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;
import javax.imageio.ImageIO;
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

    private static BufferedImage thiseas;
    private static BufferedImage minotaur;

    private final JFrame frame;
    private final JButton playAgain = new JButton("Play again");
    private final JButton seeHighScores = new JButton("Check HighScore table");
    private final JButton back_to_menu = new JButton("Back to Menu");
    private final JButton exit = new JButton("Exit");
    private HighScoreFrame highScoreFrame;

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

        JLabel minLabel = new JLabel();
        FrameSetter.scaleImgToLabel(minLabel, 350, 0, 120, 100, minotaur);
        frame.add(minLabel);
        JLabel thLabel = new JLabel();
        FrameSetter.scaleImgToLabel(thLabel, 365, 100, 100, 70, thiseas);
        frame.add(thLabel);

        GraphicPane graphicPane = new GraphicPane("VICTORY!", 800, 100, new Color(23, 131, 59), new Font("Times new Roman", Font.BOLD, 60));
        graphicPane.setBounds(0, 150, 800, 150);
        frame.add(graphicPane);

        JLabel backgroundLabel = new JLabel();
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
        return (int) (time * multiplier * Quiz.getPercentage() + Player.getCoinsCollected() * multiplier);
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
            Main.exit();
        }
        frame.dispose();
    }

    /**
     * <p>setup.</p>
     */
    static void setup() {
        try {
            thiseas = ImageIO.read(Objects.requireNonNull(Main.class.getResourceAsStream("/thiseas2/thiseasswind.png")));
            minotaur = ImageIO.read(Objects.requireNonNull(Main.class.getResourceAsStream("/minotaur/minotaurlose3.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
