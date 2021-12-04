package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Options implements ActionListener {

    public GamePanel gp;
    JFrame frame;
    JLabel backgroundLabel = new JLabel();
    JButton returnBack = new JButton("return");
    protected JButton showGuide = new JButton("show Guide");
    JButton restart = new JButton("restart");
    JButton end = new JButton("exit");
    protected static boolean isActive = false;

    public Options(GamePanel gp) {
        isActive = true;
        this.gp = gp;
        frame = new JFrame();
        frame.setTitle("Options"); //setTitle of frame
        FrameSetter.setFrame(frame,"Options",600,650);
        //Θέτω το κουμπί της εξόδου να κάνει αυτόματα click το return για να μην κολλήσει η ροή του LabyrinthFrame
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                returnBack.doClick();
            }
        });
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        ButtonSetter.setButton(returnBack,225,200,150,50,"Calibri",20,this,2);
        ButtonSetter.setButton(showGuide,225,300,150,50,"Calibri",20,this,2);
        ButtonSetter.setButton(restart,225,400,150,50,"Calibri",20,this,2);
        ButtonSetter.setButton(end,225,500,150,50,"Calibri",20,this,2);
        /*setButton(returnBack, 200);
        setButton(showGuide, 300);
        setButton(restart, 400);
        setButton(end, 500);*/

        frame.add(returnBack);
        frame.add(showGuide);
        frame.add(restart);
        frame.add(end);

        FrameSetter.scaleBackground(backgroundLabel, 600, 650);
        frame.add(backgroundLabel);
    }

    /*public void setButton(JButton button, int y) {
        button.setBounds(225, y, 150, 50);
        button.setFocusable(false);
        button.addActionListener(this);
        button.setHorizontalAlignment(JButton.CENTER);
        button.setFont(new Font("Calibri", Font.ITALIC, 20));
    }*/

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == returnBack) {
            frame.dispose();
        } else if (e.getSource() == showGuide) {
            new Guide(this);
            showGuide.setEnabled(false);
            return;
        } else if (e.getSource() == restart) {
            LabyrinthFrame.closeFrame();
            SwingUtilities.invokeLater(LabyrinthFrame::new);
            frame.dispose();
        } else {
            System.exit(1);
        }
        //Για να μην κολλήσει το progressBar
        if (LabyrinthFrame.hasStarted) {
            gp.gameState = gp.playState;
            LabyrinthFrame.updateBar(0);
            isActive = false;
        }
        KeyHandler.escPressed = false;

    }
}