package game;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeathFrame implements ActionListener {

    JFrame frame;
    JLabel backgroundLabel = new JLabel();
    JButton tryAgain = new JButton("try again");
    JButton back_to_menu = new JButton("back to Menu");
    JButton exit = new JButton("exit");

    public DeathFrame() {
        frame = new JFrame();
        FrameSetter.setFrame(frame, "Defeat", 600, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setIconImage(new ImageIcon("src/main/resources/icons/grave.png").getImage());
        ButtonSetter.setButton(tryAgain, 200, 200, 150, 50, "Calibri", 20, this, 1);
        ButtonSetter.setButton(back_to_menu, 200, 300, 150, 50, "Calibri", 20, this, 1);
        ButtonSetter.setButton(exit, 200, 400, 150, 50, "Calibri", 20, this, 1);

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
            //ΙΔΕΑ -> ερώτηση για το αν θέλει να κάνει save progress για high score
            System.exit(0);
        }
        frame.dispose();
    }
}
