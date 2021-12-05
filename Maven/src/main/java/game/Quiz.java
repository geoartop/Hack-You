package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * Κλάση για τη φόρτωση random ερωτήσεων στον χρήστη προς απάντηση
 *
 * @author Team Hack-You
 */
public class Quiz implements ActionListener {
    private ArrayList<String> questions = new ArrayList<>();
    private ArrayList<String> options = new ArrayList<>();
    private ArrayList<Character> answers = new ArrayList<>();
    char answer;
    //Για να επιλέγονται randomly οι ερωτήσεις
    private final Random random = new Random();
    private int index;

    JFrame frame = new JFrame();
    JTextArea textArea = new JTextArea();

    JButton[] buttons = new JButton[4];
    char[] symbols = {'A', 'B', 'C', 'D'};

    JLabel label = new JLabel();
    JLabel[] labels = new JLabel[4];

    GamePanel gp;

    public Quiz(GamePanel gp) throws FileNotFoundException {
        this.gp = gp;
        FrameSetter.setFrame(frame, "Question", 700, 550);
        //Για να μη γίνεται skip της ερώτησης
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        textArea.setBounds(0, 0, 700, 100);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        //textArea.setBackground(new Color(25, 25, 25));
        textArea.setOpaque(true);
        textArea.setForeground(new Color(25, 255, 0));
        textArea.setFont(new Font("Calibri", Font.BOLD, 20));
        textArea.setBorder(BorderFactory.createBevelBorder(1));
        textArea.setEditable(false);

        setLabels();
        setButtons();
        for (JLabel label : labels)
            frame.add(label);
        for (JButton button : buttons)
            frame.add(button);
        frame.add(textArea);
        frame.setVisible(true);
        readQuestions();
        // Τυχαία επιλογή μιας ερώτησης
        index = random.nextInt(questions.size());
        displayQuestion();

        FrameSetter.scaleBackground(label, 700, 550);
        //Για να εμφανίζεται στο κέντρο της οθόνης του χρήστη
        frame.add(label);
    }

    private void displayQuestion() {
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
            labels[i].setForeground(new Color(0, 72, 255));
            labels[i].setFont(new Font("Calibri", Font.PLAIN, 20));
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < buttons.length; i++) {
            if (e.getSource() == buttons[i]) {
                answer = symbols[i];
                break;
            }
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
        LabyrinthFrame.updateBar(time);
        frame.dispose();
        KeyHandler.quizTrig = false;

    }

    /**
     * Μέθοδος φόρτωσης αρχείων στα ArrayList
     *
     * @throws FileNotFoundException
     */
    private void readQuestions() throws FileNotFoundException {
        Scanner q = new Scanner(new File(String.format("src/main/resources/quiz/%s Questions.txt", Levels.difficulty)));
        while (q.hasNextLine()) {
            questions.add(q.nextLine());
        }
        Scanner o = new Scanner(new File(String.format("src/main/resources/quiz/%s Options.txt", Levels.difficulty)));
        while (o.hasNextLine()) {
            options.add(o.nextLine());
        }
        Scanner a = new Scanner(new File(String.format("src/main/resources/quiz/%s Answers.txt", Levels.difficulty)));
        while (a.hasNext()) {
            answers.add(a.next().charAt(0));
        }
    }
}