package game;

import javax.swing.*;
import java.awt.*;
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
public class Quiz extends JFrame implements ActionListener {

    private static ArrayList<String> questions = new ArrayList<>();
    private static ArrayList<String> options = new ArrayList<>();
    private static ArrayList<Character> answers = new ArrayList<>();
    char answer;
    //Για να επιλέγονται randomly οι ερωτήσεις
    private final SecureRandom random = new SecureRandom();
    private int index;

    JFrame frame = new JFrame();
    JTextArea textArea = new JTextArea();

    JButton[] buttons = new JButton[4];
    char[] symbols = {'A', 'B', 'C', 'D'};

    JLabel label = new JLabel();
    JLabel[] labels = new JLabel[4];

    GamePanel gp;

    public Quiz(GamePanel gp) {
        this.gp = gp;
        FrameSetter.setFrame(frame, "Question", 700, 540);
        //Για να μη γίνεται skip της ερώτησης
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        textArea.setBounds(100, 0, 600, 100);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setOpaque(false);
        textArea.setForeground(Color.black);
        textArea.setFont(new Font("Calibri", Font.BOLD, 20));
        //textArea.setBorder(BorderFactory.createBevelBorder(1));
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

        GraphicPane graphicPane = new GraphicPane(questions.get(index),700,50,Color.black,17,2);
        System.out.println(questions.get(index).length());
        graphicPane.setBounds(0, 0, 700, 100);
        frame.add(graphicPane);

        displayAnswers();

        FrameSetter.scaleBackground(label, 700, 550);
        //Για να εμφανίζεται στο κέντρο της οθόνης του χρήστη
        frame.add(label);
    }

    private void displayAnswers() {
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
        ButtonSetter.playSE();
        for (int i = 0; i < buttons.length; i++) {
            //Για να εμφανιστούν η σωστή και οι λάθος απαντήσεις
            if(symbols[i] == answers.get(index)){
                buttons[i].setBackground(Color.green);
            }else {
                buttons[i].setBackground(Color.red);
            }
            if (e.getSource() == buttons[i]) {
                answer = symbols[i];
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
        gp.labyrinthFrame.updateBar(time);
        frame.dispose();
        gp.keyH.quizTrig = false;

    }

    /**
     * Μέθοδος φόρτωσης αρχείων στα ArrayList
     */
    protected static void readQuestions() throws FileNotFoundException {
        Scanner q = new Scanner(new File(String.format("src/main/resources/quiz/%s Questions.txt", Levels.difficulty)));
        while (q.hasNextLine())
            questions.add(q.nextLine());
        Scanner o = new Scanner(new File(String.format("src/main/resources/quiz/%s Options.txt", Levels.difficulty)));
        while (o.hasNextLine())
            options.add(o.nextLine());
        Scanner a = new Scanner(new File(String.format("src/main/resources/quiz/%s Answers.txt", Levels.difficulty)));
        while (a.hasNext())
            answers.add(a.next().charAt(0));
    }

}