package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.security.SecureRandom;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Scanner;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

/**
 * <p>Παράθυρο φόρτωσης random ερωτήσεων στον χρήστη προς απάντηση</p>
 *
 * @author Team Hack-You
 * @version 1.0
 */
public final class Quiz implements ActionListener {

    private static final LinkedList<String> questions = new LinkedList<>();
    private static final LinkedList<String> options = new LinkedList<>();
    private static final LinkedList<Character> answers = new LinkedList<>();
    //Λίστα που αποθηκεύει τα εμφανιζόμενα indexes
    private static final LinkedList<Integer> indexes = new LinkedList<>();
    private char answer;
    //Για να επιλέγονται randomly οι ερωτήσεις
    private final SecureRandom random = new SecureRandom();
    private int index;

    private final JFrame frame = new JFrame();
    private final JTextPane textArea = new JTextPane();

    private final JButton[] buttons = new JButton[4];
    private final char[] symbols = {'A', 'B', 'C', 'D'};

    private final JLabel backgroundLabel = new JLabel();
    private final JLabel[] labels = new JLabel[4];

    private final GamePanel gp;

    private static int rightQuestions = 0;
    private static int totalQuestions = 0;

    /**
     * <p>Constructor for Quiz.</p>
     *
     * @param gp a {@link game.GamePanel} object
     */
    public Quiz(GamePanel gp) {
        this.gp = gp;
        totalQuestions++;
        FrameSetter.setFrame(frame, "Question", 800, 540);
        //Για να μη γίνεται skip της ερώτησης
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        textArea.setBounds(100, 0, 600, 100);
        textArea.setOpaque(false);
        textArea.setForeground(Color.black);
        textArea.setFont(new Font("Calibri", Font.BOLD, 22));
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
     * <p>Εμφάνιση απαντήσεων</p>
     */
    private void displayAnswers() {
        StyledDocument doc = textArea.getStyledDocument();
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        doc.setParagraphAttributes(0, doc.getLength(), center, false);
        try {
            doc.insertString(doc.getLength(), String.format("%s", questions.get(index)), null);
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
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
            if (e.getSource() == buttons[i]) {
                answer = symbols[i];
            }

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
            rightQuestions++;
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
        InputStream is = Quiz.class.getResourceAsStream(String.format("/quiz/%s Questions.txt", Levels.getDifficulty()));
        Scanner q = new Scanner(Objects.requireNonNull(is), "UTF-8");
        while (q.hasNextLine()) {
            questions.add(q.nextLine());
        }
        q.close();
        is = Quiz.class.getResourceAsStream(String.format("/quiz/%s Options.txt", Levels.getDifficulty()));
        Scanner o = new Scanner(Objects.requireNonNull(is), "UTF-8");
        while (o.hasNextLine()) {
            options.add(o.nextLine());
        }
        o.close();
        is = Quiz.class.getResourceAsStream(String.format("/quiz/%s Answers.txt", Levels.getDifficulty()));
        Scanner a = new Scanner(Objects.requireNonNull(is), "UTF-8");
        while (a.hasNext()) {
            answers.add(a.next().charAt(0));
        }
        a.close();
    }

    /**
     * <p>getPercentage of right answered questions.</p>
     *
     * @return a double
     */
    public static double getPercentage() {
        return (double) rightQuestions / totalQuestions;
    }

    /**
     * <p>clearIndexes and reset question and coin metrics values.</p>
     */
    public static void clearIndexes() {
        Player.restoreCoinsCollected();
        totalQuestions = 0;
        rightQuestions = 0;
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
