package game;

import javax.swing.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Παράθυρο φόρτωσης random ερωτήσεων στον χρήστη προς απάντηση
 *
 * @author Team Hack-You
 */
//TODO(Mallikoko): Φτιάξε καλύτερα την εμφάνιση του παραθύρου !!!!!
public final class Quiz extends JFrame implements ActionListener {

    private final static ArrayList<String> questions = new ArrayList<>();
    private final static ArrayList<String> options = new ArrayList<>();
    private final static ArrayList<Character> answers = new ArrayList<>();
    private char answer;
    //Για να επιλέγονται randomly οι ερωτήσεις
    private final SecureRandom random = new SecureRandom();
    private final int index;

    private final JFrame frame = new JFrame();
    private final JTextArea textArea = new JTextArea();

    private final JButton[] buttons = new JButton[4];
    private final char[] symbols = {'A', 'B', 'C', 'D'};

    private final JLabel label = new JLabel();
    private final JLabel[] labels = new JLabel[4];

    private final GamePanel gp;

    public Quiz(GamePanel gp) {
        this.gp = gp;
        FrameSetter.setFrame(frame, "Question", 700, 540);
        //Για να μη γίνεται skip της ερώτησης
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        textArea.setBounds(100, 0, 520, 100);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setOpaque(false);
        textArea.setForeground(Color.black);
        textArea.setFont(new Font("Calibri", Font.BOLD, 22));
        //textArea.setBorder(BorderFactory.createBevelBorder(1));
        textArea.setBorder(BorderFactory.createEmptyBorder());
        textArea.setEditable(false);

        setLabels();
        setButtons();
        for (JLabel label : labels)
            frame.add(label);
        for (JButton button : buttons)
            frame.add(button);
        frame.add(textArea);
        frame.setVisible(true);
        // Τυχαία επιλογή μιας ερώτησης
        index = random.nextInt(questions.size());

        /*GraphicPane graphicPane = new GraphicPane(questions.get(index), 700, 50, Color.black, 17, 2);
        graphicPane.setBounds(0, 0, 700, 100);
        frame.add(graphicPane);*/

        displayAnswers();

        FrameSetter.scaleBackground(label, 700, 550);
        //Για να εμφανίζεται στο κέντρο της οθόνης του χρήστη
        frame.add(label);
    }

    /**
     * Εμφάνιση απαντήσεων
     */
    private void displayAnswers() {
        textArea.setText(questions.get(index));
        for (int i = 0; i < labels.length; i++)
            labels[i].setText(options.get(4 * index + i));

    }

    private void setButtons() {
        for (int i = 0; i < buttons.length; i++) {
            buttons[i] = new JButton(String.valueOf(symbols[i]));
            ButtonSetter.setButton(buttons[i], 0, (i + 1) * 100, 100, 100, "Calibri", 35, this, 1);
        }
    }

    private void setLabels() {
        for (int i = 0; i < labels.length; i++) {
            labels[i] = new JLabel();
            labels[i].setBounds(125, (i + 1) * 100, 500, 100);
            labels[i].setBackground(new Color(50, 50, 50));
            labels[i].setForeground(new Color(134, 1, 1, 196));
            labels[i].setFont(new Font("Calibri", Font.BOLD, 22));
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        ButtonSetter.playSE();
        for (int i = 0; i < buttons.length; i++) {
            //Για να εμφανιστούν η σωστή και οι λάθος απαντήσεις
            if (symbols[i] == answers.get(index)) {
                buttons[i].setBackground(Color.green);
            } else {
                buttons[i].setBackground(Color.red);
            }
            if (e.getSource() == buttons[i])
                answer = symbols[i];

        }
        checkAnswer();
        frame.dispose();
    }

    private void correctAnswer() {
        JOptionPane.showMessageDialog(null, "Correct answer!", "Review", JOptionPane.INFORMATION_MESSAGE);
    }

    private void wrongAnswer() {
        JOptionPane.showMessageDialog(null, "Wrong answer!", "Review", JOptionPane.ERROR_MESSAGE);
    }

    private void checkAnswer() {
        int time;
        if (answer == answers.get(index)) {
            time = LabyrinthFrame.for_correct;
            correctAnswer();
        } else {
            time = LabyrinthFrame.for_wrong;
            wrongAnswer();
        }
        //Για να μην κολλήσει το progressBar
        gp.gameState = gp.playState;
        gp.labyrinthFrame.updateBar(time);
        frame.dispose();
        gp.keyH.setQuizTrig(false);

    }

    /**
     * Φόρτωση αρχείων στα ArrayList
     */
    static void readQuestions() throws FileNotFoundException {
        Scanner q = new Scanner(new File(String.format("src/main/resources/quiz/%s Questions.txt", Levels.getDifficulty())),"UTF-8");
        while (q.hasNextLine())
            questions.add(q.nextLine());
        Scanner o = new Scanner(new File(String.format("src/main/resources/quiz/%s Options.txt", Levels.getDifficulty())),"UTF-8");
        while (o.hasNextLine())
            options.add(o.nextLine());
        Scanner a = new Scanner(new File(String.format("src/main/resources/quiz/%s Answers.txt", Levels.getDifficulty())),"UTF-8");
        while (a.hasNext())
            answers.add(a.next().charAt(0));
    }

}