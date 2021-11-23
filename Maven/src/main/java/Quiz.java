/*import java.awt.event.*;
import java.awt.*;
import java.util.Random;
import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;



public class Quiz implements ActionListener {
    ArrayList<String> questions = new ArrayList<String>();
    ArrayList<String> options = new ArrayList<String>();
    ArrayList<Character> answers = new ArrayList<Character>();
    char answer;
    Random random = new Random();
    int index;

    JFrame frame = new JFrame();
    JTextField textField = new JTextField();
    JTextArea textArea = new JTextArea();
    JButton buttonA = new JButton();
    JButton buttonB = new JButton();
    JButton buttonC = new JButton();
    JButton buttonD = new JButton();
    JLabel labelA = new JLabel();
    JLabel labelB = new JLabel();
    JLabel labelC = new JLabel();
    JLabel labelD = new JLabel();

    public Quiz() throws FileNotFoundException {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 550);
        frame.setLayout(null);
        frame.setResizable(false);

        textField.setBounds(0, 0, 700, 50);
        textField.setFont(new Font("Calibri", Font.BOLD, 25));
        textField.setBorder(BorderFactory.createBevelBorder(1));
        textField.setHorizontalAlignment(JTextField.CENTER);
        textField.setEditable(false);


        textArea.setBounds(0, 50, 700, 50);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setBackground(new Color(25, 25, 25));
        textArea.setForeground(new Color(25, 255, 0));
        textArea.setFont(new Font("Calibri", Font.BOLD, 25));
        textArea.setBorder(BorderFactory.createBevelBorder(1));
        textArea.setEditable(false);

        buttonA.setBounds(0, 100, 100, 100);
        buttonA.setFont(new Font("Calibri", Font.BOLD, 35));
        buttonA.setFocusable(false);
        buttonA.addActionListener(this);
        buttonA.setText("A");

        buttonB.setBounds(0, 200, 100, 100);
        buttonB.setFont(new Font("Calibri", Font.BOLD, 35));
        buttonB.setFocusable(false);
        buttonB.addActionListener(this);
        buttonB.setText("B");

        buttonC.setBounds(0, 300, 100, 100);
        buttonC.setFont(new Font("Calibri", Font.BOLD, 35));
        buttonC.setFocusable(false);
        buttonC.addActionListener(this);
        buttonC.setText("C");

        buttonD.setBounds(0, 400, 100, 100);
        buttonD.setFont(new Font("Calibri", Font.BOLD, 35));
        buttonD.setFocusable(false);
        buttonD.addActionListener(this);
        buttonD.setText("D");

        labelA.setBounds(125, 100, 500, 100);
        labelA.setBackground(new Color(50, 50, 50));
        labelA.setForeground(new Color(0, 72, 255));
        labelA.setFont(new Font("Calibri", Font.PLAIN, 20));


        labelB.setBounds(125, 200, 500, 100);
        labelB.setBackground(new Color(50, 50, 50));
        labelB.setForeground(new Color(0, 72, 255));
        labelB.setFont(new Font("Calibri", Font.PLAIN, 20));

        labelC.setBounds(125, 300, 500, 100);
        labelC.setBackground(new Color(50, 50, 50));
        labelC.setForeground(new Color(0, 72, 255));
        labelC.setFont(new Font("Calibri", Font.PLAIN, 20));

        labelD.setBounds(125, 400, 500, 100);
        labelD.setBackground(new Color(50, 50, 50));
        labelD.setForeground(new Color(0, 72, 255));
        labelD.setFont(new Font("Calibri", Font.PLAIN, 20));

        frame.add(labelA);
        frame.add(labelB);
        frame.add(labelC);
        frame.add(labelD);
        frame.add(buttonA);
        frame.add(buttonB);
        frame.add(buttonC);
        frame.add(buttonD);
        frame.add(textArea);
        frame.add(textField);
        frame.setVisible(true);


        readQuestions();
        index = random.nextInt(questions.size());

        displayQuestion();

    }

    public void displayQuestion() {
        textField.setText("Game");
        textArea.setText(questions.get(index));
        labelA.setText(options.get(4 * index));
        labelB.setText(options.get(4 * index + 1));
        labelC.setText(options.get(4 * index + 2));
        labelD.setText(options.get(4 * index + 3));

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == buttonA) {
            answer = 'A';
        } else if (e.getSource() == buttonB) {
            answer = 'B';
        } else if (e.getSource() == buttonC) {
            answer = 'C';

        } else if (e.getSource() == buttonD) {
            answer = 'D';
        }
        checkAnswer();
        frame.dispose();


    }

    public void correctAnswer() {
        JOptionPane.showMessageDialog(null, "Correct answer!", "Review", JOptionPane.INFORMATION_MESSAGE);

    }

    public void wrongAnswer() {
        JOptionPane.showMessageDialog(null, "Wrong answer!", "Review", JOptionPane.ERROR_MESSAGE);

    }

    public void checkAnswer() {
        if (answer == answers.get(index)) {
            correctAnswer();

        } else wrongAnswer();

    }

    public void readQuestions() throws FileNotFoundException {
        Scanner q = new Scanner(new File("src/Easy Questions.txt"));
        while (q.hasNextLine()) {
            questions.add(q.nextLine());
        }
        Scanner o = new Scanner(new File("src/Easy Options.txt"));
        while (o.hasNextLine()) {
            options.add(o.nextLine());
        }
        Scanner a = new Scanner(new File("src/Easy Answers.txt"));
        while (a.hasNext()) {
            answers.add(a.next().charAt(0));
        }
    }
}*/



