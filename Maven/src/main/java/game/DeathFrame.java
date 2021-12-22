package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Παράθυρο που εμφανίζεται όταν ο παίκτης χάνει
 *
 * @author Team Hack-You
 * @version 1.0
 */
public final class DeathFrame implements ActionListener {

    private final int X = 215;
    private final int Y = 200;
    private final int WIDTH = 150;
    private final int HEIGHT = 50;


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
        ButtonSetter.setButton(tryAgain, X, Y, WIDTH, HEIGHT, new Font("Calibri", Font.ITALIC, 20), this);
        ButtonSetter.setButton(back_to_menu, X, Y + 100, WIDTH, HEIGHT, new Font("Calibri", Font.ITALIC, 20), this);
        ButtonSetter.setButton(exit, X, Y + 200, WIDTH, HEIGHT, new Font("Calibri", Font.ITALIC, 20), this);

        GraphicPane graphicPane = new GraphicPane("GAME OVER", 600, 50, Color.red, new Font("Times new Roman", Font.BOLD, 40));

        graphicPane.setBounds(0, 100, 600, 125);
        frame.add(graphicPane);

        FrameSetter.scaleImgToLabel(headLabel, 220, 20, 100, 80, new ImageIcon("src/main/resources/deadthiseas/dead3.png"));
        FrameSetter.scaleImgToLabel(minLabel, 300, 0, 125, 100, new ImageIcon("src/main/resources/minotaur/minotaurwin.png"));
        frame.add(minLabel);
        frame.add(headLabel);
        frame.add(tryAgain);
        frame.add(back_to_menu);
        frame.add(exit);
        FrameSetter.scaleBackground(backgroundLabel, 600, 600);
        frame.add(backgroundLabel);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == tryAgain) {
            SwingUtilities.invokeLater(LabyrinthFrame::new);
            Quiz.indexes.clear();
        } else if (e.getSource() == back_to_menu) {
            SwingUtilities.invokeLater(Menu::new);
            Quiz.indexes.clear();
        } else {
            System.exit(0);
        }
        frame.dispose();
    }
}
