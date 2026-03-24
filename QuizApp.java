import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class QuizApp extends JFrame implements ActionListener {

    JLabel questionLabel;
    JRadioButton opt1, opt2, opt3, opt4;
    ButtonGroup bg;
    JButton nextBtn, prevBtn, submitBtn;

    int current = 0;
    int score = 0;

    int[] answers = new int[10]; // stores user answers

    String[][] questions = {
        {"What is the capital of India?", "Mumbai", "Delhi", "Kolkata", "Chennai"},
        {"Which language runs in a web browser?", "Java", "C", "Python", "JavaScript"},
        {"Who developed Java?", "Microsoft", "Apple", "Sun Microsystems", "Google"},
        {"Which is not OOP concept?", "Inheritance", "Encapsulation", "Compilation", "Polymorphism"},
        {"Which keyword is used to inherit a class?", "this", "super", "extends", "implements"},
        {"Which method is entry point in Java?", "start()", "main()", "run()", "init()"},
        {"Which is platform independent?", "C", "C++", "Java", "Assembly"},
        {"Which is not a Java feature?", "Object-oriented", "Portable", "Use of pointers", "Secure"},
        {"Which company owns Java now?", "Sun", "Oracle", "Microsoft", "IBM"},
        {"Which package contains Scanner class?", "java.util", "java.io", "java.lang", "java.net"}
    };

    int[] correctAnswers = {2, 4, 3, 3, 3, 2, 3, 3, 2, 1};

    public QuizApp() {
        setTitle("MCQ Quiz Application");
        setSize(600, 300);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        questionLabel = new JLabel();
        questionLabel.setFont(new Font("Arial", Font.BOLD, 16));
        add(questionLabel, BorderLayout.NORTH);

        opt1 = new JRadioButton();
        opt2 = new JRadioButton();
        opt3 = new JRadioButton();
        opt4 = new JRadioButton();

        bg = new ButtonGroup();
        bg.add(opt1);
        bg.add(opt2);
        bg.add(opt3);
        bg.add(opt4);

        JPanel optionsPanel = new JPanel(new GridLayout(4,1));
        optionsPanel.add(opt1);
        optionsPanel.add(opt2);
        optionsPanel.add(opt3);
        optionsPanel.add(opt4);

        add(optionsPanel, BorderLayout.CENTER);

        nextBtn = new JButton("Next");
        prevBtn = new JButton("Previous");
        submitBtn = new JButton("Submit");

        nextBtn.addActionListener(this);
        prevBtn.addActionListener(this);
        submitBtn.addActionListener(this);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(prevBtn);
        buttonPanel.add(nextBtn);
        buttonPanel.add(submitBtn);

        add(buttonPanel, BorderLayout.SOUTH);

        loadQuestion(current);
        setVisible(true);
    }

    void loadQuestion(int index) {
        questionLabel.setText("Q" + (index+1) + ": " + questions[index][0]);
        opt1.setText(questions[index][1]);
        opt2.setText(questions[index][2]);
        opt3.setText(questions[index][3]);
        opt4.setText(questions[index][4]);

        bg.clearSelection();

        if (answers[index] != 0) {
            switch (answers[index]) {
                case 1: opt1.setSelected(true); break;
                case 2: opt2.setSelected(true); break;
                case 3: opt3.setSelected(true); break;
                case 4: opt4.setSelected(true); break;
            }
        }
    }

    void saveAnswer() {
        if (opt1.isSelected()) answers[current] = 1;
        else if (opt2.isSelected()) answers[current] = 2;
        else if (opt3.isSelected()) answers[current] = 3;
        else if (opt4.isSelected()) answers[current] = 4;
    }

    public void actionPerformed(ActionEvent e) {

        saveAnswer();

        if (e.getSource() == nextBtn) {
            if (current < questions.length - 1) {
                current++;
                loadQuestion(current);
            }
        }

        if (e.getSource() == prevBtn) {
            if (current > 0) {
                current--;
                loadQuestion(current);
            }
        }

        if (e.getSource() == submitBtn) {
            score = 0;
            for (int i = 0; i < questions.length; i++) {
                if (answers[i] == correctAnswers[i]) {
                    score++;
                }
            }
            JOptionPane.showMessageDialog(this, "Your Score: " + score + "/10");
        }
    }

    public static void main(String[] args) {
        new QuizApp();
    }
} 