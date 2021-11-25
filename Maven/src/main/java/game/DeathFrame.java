package game;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeathFrame implements ActionListener {

    JFrame frame;
    JLabel label = new JLabel();
    JButton tryAgain = new JButton("try again");
    JButton switchDifficulty = new JButton("change difficulty");
    JButton exit = new JButton("exit");

    public DeathFrame() {
        frame = new JFrame();
        FrameSetter.setFrame(frame, "Defeat", 600, 600);
        frame.setIconImage(new ImageIcon("src/main/resources/grave.png").getImage());
        ButtonSetter.setButton(tryAgain, 250, 200, 100, 50, "Calibri", 25,this,1);
        ButtonSetter.setButton(switchDifficulty, 250, 300, 100, 50, "Calibri", 25,this,1);
        ButtonSetter.setButton(exit, 250, 400, 100, 50, "Calibri", 25,this,1);
        /*tryAgain.addActionListener(this);
        switchDifficulty.addActionListener(this);
        exit.addActionListener(this);*/

        frame.add(tryAgain);
        frame.add(switchDifficulty);
        frame.add(exit);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == tryAgain) {
            SwingUtilities.invokeLater(LabyrinthFrame::new);
        } else if (e.getSource() == switchDifficulty) {
            SwingUtilities.invokeLater(Levels::new);
        } else {
            //ΙΔΕΑ -> ερώτηση για το αν θέλει να κάνει save progress για high score
            System.exit(0);
        }
        frame.dispose();
    }
}
