package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Παράθυρο που εμφανίζεται όταν ο παίκτης χάνει
 *
 * @author Team Hack-You
 */
public final class DeathFrame implements ActionListener {

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
        ButtonSetter.setButton(tryAgain, 215, 200, 150, 50, new Font("Calibri", Font.ITALIC, 20),this);
        ButtonSetter.setButton(back_to_menu, 215, 300, 150, 50,  new Font("Calibri", Font.ITALIC, 20),this);
        ButtonSetter.setButton(exit, 215, 400, 150, 50,  new Font("Calibri", Font.ITALIC, 20),this);

        GraphicPane graphicPane = new GraphicPane("GAME OVER", 600, 50, Color.red,new Font("Times new Roman", Font.BOLD,40));

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
        } else if (e.getSource() == back_to_menu) {
            SwingUtilities.invokeLater(Menu::new);
        } else {
            System.exit(0);
        }
        frame.dispose();
    }
}
