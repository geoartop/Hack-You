package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class QuizFrame implements ActionListener {

    JFrame frame;
    JLabel label = new JLabel();
    JRadioButton[] radioButtons = new JRadioButton[4];
    ButtonGroup buttonGroup = new ButtonGroup();
    private final ImageIcon icon = new ImageIcon("src/main/resources/icons/question_mark.png");
    JButton submit = new JButton("submit");
    private final int RADIOBUTTON_GAP = 60;
    private final int RADIOBUTTON_START = 50;
    private final int RADIOBUTTON_Y = 400;
    private final int RADIOBUTTON_WIDTH = 50;
    private final int RADIOBUTTON_HEIGHT = 50;

    public QuizFrame() {
        frame = new JFrame();
        frame.setTitle("Question"); //setTitle of frame
        //Για να μην μπορεί να γίνει skip η ερώτηση
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setResizable(false);
        frame.setSize(600, 600);
        frame.setVisible(true);
        frame.setLayout(null);
        frame.setIconImage(icon.getImage());
        //Για να εμφανίζεται στο κέντρο της οθόνης του χρήστη
        frame.setLocationRelativeTo(null);

        label.setBounds(30, 40, 400, 30);
        frame.add(label);
        for (int i = 0; i < radioButtons.length; i++) {
            radioButtons[i] = new JRadioButton();
            setRadioButton(i, i * RADIOBUTTON_GAP + RADIOBUTTON_START);
            frame.add(radioButtons[i]);
            buttonGroup.add(radioButtons[i]);
        }
        setButton(submit);

        frame.add(submit);


    }

    private void setData() {

    }

    //Θα γίνεται μέσω της μεθόδου radiobutton[i].isSelected()
    private boolean checkAns() {
        return true;
    }

    public void setRadioButton(int i, int x) {
        radioButtons[i].setBounds(x, RADIOBUTTON_Y, RADIOBUTTON_WIDTH, RADIOBUTTON_HEIGHT);
        radioButtons[i].setText(String.format("%d", i + 1));
        radioButtons[i].setFocusable(false);
    }


    public void setButton(JButton button) {
        button.setBounds(250, 500, 100, 50);
        button.setFocusable(false);
        button.addActionListener(this);
        button.setHorizontalAlignment(JButton.CENTER);
        button.setBackground(Color.GREEN);
        button.setFont(new Font("Calibri", Font.ITALIC, 20));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submit) {
            boolean flag = false;
            for (JRadioButton radioButton : radioButtons) {
                if (radioButton.isSelected()) {
                    flag = true;
                    break;
                }
            }
            if (!flag) {
                JOptionPane.showMessageDialog(null, "Please select an answer", "Warning", JOptionPane.WARNING_MESSAGE);
                return;
            }
            int time = radioButtons[3].isSelected() ? LabyrinthFrame.for_correct : LabyrinthFrame.for_wrong;
            //gp.lb.updateBar(time);
            frame.dispose();
        }
    }
}