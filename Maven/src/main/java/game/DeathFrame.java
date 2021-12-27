package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * <p>Παράθυρο που εμφανίζεται όταν ο παίκτης χάνει</p>
 *
 * @author Team Hack-You
 * @version 1.0
 */
public final class DeathFrame implements ActionListener {

    private int y = 200;

    private final JFrame frame;
    private final JLabel backgroundLabel = new JLabel();
    private final JButton tryAgain = new JButton("try again");
    private final JButton back_to_menu = new JButton("back to Menu");
    private final JButton exit = new JButton("exit");
    private final JLabel headLabel = new JLabel();
    private final JLabel minLabel = new JLabel();

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

        FrameSetter.scaleImgToLabel(headLabel, 220, 20, 100, 80, new ImageIcon("src/main/resources/deadthiseas/dead3.png"));
        FrameSetter.scaleImgToLabel(minLabel, 300, 0, 125, 100, new ImageIcon("src/main/resources/minotaur/minotaurwin.png"));
        frame.add(minLabel);
        frame.add(headLabel);

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
            System.exit(0);
        }
        Quiz.clearIndexes();
        frame.dispose();
    }
}
