package game;

import javax.swing.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.security.SecureRandom;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * <p>Παράθυρο φόρτωσης random ερωτήσεων στον χρήστη προς απάντηση</p>
 *
 * @author Team Hack-You
 * @version 1.0
 */
public final class Quiz implements ActionListener {

    private final static LinkedList<String> questions = new LinkedList<>();
    private final static LinkedList<String> options = new LinkedList<>();
    private final static LinkedList<Character> answers = new LinkedList<>();
    //Λίστα που αποθηκεύει τα εμφανιζόμενα indexes
    private final static LinkedList<Integer> indexes = new LinkedList<>();
    private char answer;
    //Για να επιλέγονται randomly οι ερωτήσεις
    private final SecureRandom random = new SecureRandom();
    private int index;

    private final JFrame frame = new JFrame();
    private final JTextArea textArea = new JTextArea();

    private final JButton[] buttons = new JButton[4];
    private final char[] symbols = {'A', 'B', 'C', 'D'};

    private final JLabel backgroundLabel = new JLabel();
    private final JLabel[] labels = new JLabel[4];

    private final GamePanel gp;

    /**
     * <p>Constructor for Quiz.</p>
     *
     * @param gp a {@link game.GamePanel} object
     */
    public Quiz(GamePanel gp) {
        this.gp = gp;
        FrameSetter.setFrame(frame, "Question", 800, 540);
        //Για να μη γίνεται skip της ερώτησης
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        textArea.setBounds(100, 0, 620, 100);
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

        for (JLabel label : labels) {
            frame.add(label);
        }
        for (JButton button : buttons) {
            frame.add(button);
            button.setIcon(null);
        }
        frame.add(textArea);
        frame.setVisible(true);
        // Τυχαία επιλογή μιας ερώτησης που δεν έχει ξαναεμφανιστεί στο ίδιο παιχνίδι
        setIndex();

        displayAnswers();

        FrameSetter.scaleBackground(backgroundLabel, 800, 550);
        //Για να εμφανίζεται στο κέντρο της οθόνης του χρήστη
        frame.add(backgroundLabel);
    }

    /**
     * <p>Generate and add index of question</p>
     */
    private void setIndex() {
        index = random.nextInt(questions.size());
        while (indexes.contains(index)) {
            index = random.nextInt(questions.size());
        }
        indexes.add(index);

    }

    /**
     * Εμφάνιση απαντήσεων
     */
    private void displayAnswers() {
        textArea.setText(questions.get(index));
        for (int i = 0; i < labels.length; i++) {
            labels[i].setText(options.get(4 * index + i));
        }

    }

    /**
     * <p>setButtons.</p>
     */
    private void setButtons() {
        for (int i = 0; i < buttons.length; i++) {
            buttons[i] = new JButton(String.valueOf(symbols[i]));
            ButtonSetter.setButton(buttons[i], 0, (i + 1) * 100, 100, 100, new Font("Calibri", Font.BOLD, 35), this);
            buttons[i].setBackground(new Color(255, 245, 225, 255));
        }
    }

    private void setLabels() {
        for (int i = 0; i < labels.length; i++) {
            labels[i] = new JLabel();
            labels[i].setBounds(125, (i + 1) * 100, 500, 100);
            labels[i].setForeground(Main.mainColor);
            labels[i].setFont(new Font("Calibri", Font.BOLD, 22));
        }
    }


    /** {@inheritDoc} */
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


    /**
     * <p>JOptionPane that shows up when player answers correctly </p>
     */
    private void correctAnswer() {
        JOptionPane.showMessageDialog(null, "Correct answer!", "Review", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * <p>JOptionPane that shows up when player is wrong </p>
     */
    private void wrongAnswer() {
        JOptionPane.showMessageDialog(null, "Wrong answer!", "Review", JOptionPane.ERROR_MESSAGE);
    }

    /**
     * <p>checkAnswer.</p>
     */
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
        gp.setGameState(GamePanel.playState);
        gp.labyrinthFrame.updateBar(time);
        frame.dispose();
        gp.keyH.setQuizTrig(false);

    }

    /**
     * Φόρτωση αρχείων στα ArrayList
     *
     * @throws java.io.FileNotFoundException if any.
     */
    public static void readQuestions() throws FileNotFoundException {
        Scanner q = new Scanner(new File
                (String.format("src/main/resources/quiz/%s Questions.txt", Levels.getDifficulty())), "UTF-8");
        while (q.hasNextLine()) {
            questions.add(q.nextLine());
        }
        Scanner o = new Scanner(new File
                (String.format("src/main/resources/quiz/%s Options.txt", Levels.getDifficulty())), "UTF-8");
        while (o.hasNextLine()) {
            options.add(o.nextLine());
        }
        Scanner a = new Scanner
                (new File(String.format("src/main/resources/quiz/%s Answers.txt", Levels.getDifficulty())), "UTF-8");
        while (a.hasNext()) {
            answers.add(a.next().charAt(0));
        }
    }

    /**
     * <p>clearIndexes.</p>
     */
    public static void clearIndexes() {
        indexes.clear();
    }

    /**
     * <p>clearLists.</p>
     */
    public static void clearLists() {
        questions.clear();
        options.clear();
        answers.clear();
    }

}
