package game;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;
import javax.imageio.ImageIO;
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
 * <p>Παράθυρο που εμφανίζεται όταν ο παίκτης χάνει.</p>
 *
 * @author Team Hack-You
 * @version 1.0
 */
public final class DeathFrame implements ActionListener {

    private static BufferedImage thiseas;
    private static BufferedImage minotaur;

    private int y = 200;

    private final JFrame frame;
    private final JButton tryAgain = new JButton("try again");
    private final JButton back_to_menu = new JButton("back to Menu");
    private final JButton exit = new JButton("exit");

    /**
     * <p>Constructor for DeathFrame.</p>
     */
    public DeathFrame() {
        frame = new JFrame();
        FrameSetter.setFrame(frame, "Defeat", 600, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setIconImage(new ImageIcon("src/main/resources/icons/grave.png").getImage());

        GraphicPane graphicPane = new GraphicPane("GAME OVER", 600, 50, Color.red, new Font("Times new Roman", Font.BOLD, 40));

        graphicPane.setBounds(0, 100, 600, 125);
        frame.add(graphicPane);

        setButton(tryAgain);
        setButton(back_to_menu);
        setButton(exit);

        JLabel headLabel = new JLabel();
        FrameSetter.scaleImgToLabel(headLabel, 210, 20, 100, 80, thiseas);
        JLabel minLabel = new JLabel();
        FrameSetter.scaleImgToLabel(minLabel, 290, 0, 125, 100, minotaur);
        frame.add(minLabel);
        frame.add(headLabel);

        JLabel backgroundLabel = new JLabel();
        FrameSetter.scaleBackground(backgroundLabel, 600, 600);
        frame.add(backgroundLabel);
    }

    /**
     * <p>setButton.</p>
     *
     * @param button a {@link JButton} object
     */
    private void setButton(JButton button) {
        ButtonSetter.setButton(button, 210, y, 175, 50, Main.mainFont, this);
        frame.add(button);
        y += 100;

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == tryAgain) {
            LabyrinthFrame.setRestartStatus(true);
            SwingUtilities.invokeLater(LabyrinthFrame::new);
        } else if (e.getSource() == back_to_menu) {
            SwingUtilities.invokeLater(Menu::new);
        } else {
            Main.exit();
        }
        GamePanel.stopSE();
        Quiz.clearIndexes();
        frame.dispose();
    }

    /**
     * <p>setup.</p>
     */
    static void setup() {
        try {
            thiseas = ImageIO.read(Objects.requireNonNull(Main.class.getResourceAsStream("/deadthiseas/dead3.png")));
            minotaur = ImageIO.read(Objects.requireNonNull(Main.class.getResourceAsStream("/minotaur/minotaurwin.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
