package game;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeathFrame implements ActionListener {

    JFrame frame;
    JLabel label = new JLabel();
    JButton tryAgain = new JButton("try again");
    JButton back_to_menu = new JButton("back to Menu");
    JButton exit = new JButton("exit");

    public DeathFrame() {
        frame = new JFrame();
        FrameSetter.setFrame(frame, "Defeat", 600, 600);
        frame.setIconImage(new ImageIcon("src/main/resources/icons/grave.png").getImage());
        ButtonSetter.setButton(tryAgain, 250, 200, 150, 50, "Calibri", 20,this,1);
        ButtonSetter.setButton(back_to_menu, 250, 300, 150, 50, "Calibri", 20,this,1);
        ButtonSetter.setButton(exit, 250, 400, 150, 50, "Calibri", 20,this,1);
        /*tryAgain.addActionListener(this);
        switchDifficulty.addActionListener(this);
        exit.addActionListener(this);*/

        frame.add(tryAgain);
        frame.add(back_to_menu);
        frame.add(exit);
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
