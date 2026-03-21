import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class GuessingGame extends JFrame implements ActionListener {

    private JTextField inputField;
    private JButton guessButton, resetButton;
    private JLabel messageLabel, attemptsLabel;

    private int numberToGuess;
    private int attempts;

    public GuessingGame() {
        setTitle("Number Guessing Game");
        setSize(350, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(5, 1, 10, 10));

        // Initialize game
        startNewGame();

        messageLabel = new JLabel("Guess a number between 1 and 100", JLabel.CENTER);
        inputField = new JTextField();
        guessButton = new JButton("Guess");
        resetButton = new JButton("Reset");
        attemptsLabel = new JLabel("Attempts: 0", JLabel.CENTER);

        guessButton.addActionListener(this);
        resetButton.addActionListener(this);

        add(messageLabel);
        add(inputField);
        add(guessButton);
        add(resetButton);
        add(attemptsLabel);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void startNewGame() {
        Random rand = new Random();
        numberToGuess = rand.nextInt(100) + 1;
        attempts = 0;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == guessButton) {
            try {
                int guess = Integer.parseInt(inputField.getText());
                attempts++;

                if (guess < numberToGuess) {
                    messageLabel.setText("Too Low! Try again.");
                } else if (guess > numberToGuess) {
                    messageLabel.setText("Too High! Try again.");
                } else {
                    messageLabel.setText("Correct! 🎉 Number was " + numberToGuess);
                    JOptionPane.showMessageDialog(this,
                            "You guessed it in " + attempts + " attempts!");
                }

                attemptsLabel.setText("Attempts: " + attempts);
                inputField.setText("");

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please enter a valid number!");
            }
        }

        if (e.getSource() == resetButton) {
            startNewGame();
            messageLabel.setText("New Game! Guess 1-100");
            attemptsLabel.setText("Attempts: 0");
            inputField.setText("");
        }
    }

    public static void main(String[] args) {
        new GuessingGame();
    }
}