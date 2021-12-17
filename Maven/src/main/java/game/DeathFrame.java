package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Παράθυρο που εμφανίζεται όταν ο παίκτης χάνει
 */
public class DeathFrame implements ActionListener {

    JFrame frame;
    JLabel backgroundLabel = new JLabel();
    JButton tryAgain = new JButton("try again");
    JButton back_to_menu = new JButton("back to Menu");
    JButton exit = new JButton("exit");
    JLabel headLabel = new JLabel();

    public DeathFrame() {
        frame = new JFrame();
        FrameSetter.setFrame(frame, "Defeat", 600, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setIconImage(new ImageIcon("src/main/resources/icons/grave.png").getImage());
        ButtonSetter.setButton(tryAgain, 215, 200, 150, 50, "Calibri", 20, this, 2);
        ButtonSetter.setButton(back_to_menu, 215, 300, 150, 50, "Calibri", 20, this, 2);
        ButtonSetter.setButton(exit, 215, 400, 150, 50, "Calibri", 20, this, 2);

        GraphicPane graphicPane = new GraphicPane("YOU DIED",600,50,Color.red,40,1);

        graphicPane.setBounds(0, 100, 600, 125);
        frame.add(graphicPane);

        FrameSetter.scaleImgToLabel(headLabel,235,0,125,125,new ImageIcon("src/main/resources/deadthiseas/dead3.png"));
        frame.add(headLabel);
        frame.add(tryAgain);
        frame.add(back_to_menu);
        frame.add(exit);
        FrameSetter.scaleBackground(backgroundLabel, 600, 600);
        frame.add(backgroundLabel);
    }

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
