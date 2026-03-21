import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TicTacToe extends JFrame implements ActionListener {

    private JButton[][] buttons = new JButton[3][3];
    private boolean isXTurn = true;
    private JLabel statusLabel;
    private JButton resetButton;

    public TicTacToe() {
        setTitle("Tic-Tac-Toe");
        setSize(350, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Top status label
        statusLabel = new JLabel("Player X's Turn", JLabel.CENTER);
        statusLabel.setFont(new Font("Arial", Font.BOLD, 16));
        add(statusLabel, BorderLayout.NORTH);

        // Grid panel
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 3));

        Font btnFont = new Font("Arial", Font.BOLD, 40);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j] = new JButton("");
                buttons[i][j].setFont(btnFont);
                buttons[i][j].addActionListener(this);
                panel.add(buttons[i][j]);
            }
        }

        add(panel, BorderLayout.CENTER);

        // Reset button
        resetButton = new JButton("Reset");
        resetButton.addActionListener(e -> resetGame());
        add(resetButton, BorderLayout.SOUTH);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton btn = (JButton) e.getSource();

        if (!btn.getText().equals("")) return;

        btn.setText(isXTurn ? "X" : "O");

        if (checkWin()) {
            statusLabel.setText("Player " + (isXTurn ? "X" : "O") + " Wins!");
            disableButtons();
            JOptionPane.showMessageDialog(this,
                    "Player " + (isXTurn ? "X" : "O") + " Wins!");
        } else if (isBoardFull()) {
            statusLabel.setText("It's a Draw!");
            JOptionPane.showMessageDialog(this, "Match Draw!");
        } else {
            isXTurn = !isXTurn;
            statusLabel.setText("Player " + (isXTurn ? "X" : "O") + "'s Turn");
        }
    }

    private boolean checkWin() {
        String[][] board = new String[3][3];

        // Copy button text to board
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                board[i][j] = buttons[i][j].getText();

        // Rows & Columns
        for (int i = 0; i < 3; i++) {
            if (!board[i][0].equals("") &&
                board[i][0].equals(board[i][1]) &&
                board[i][1].equals(board[i][2])) return true;

            if (!board[0][i].equals("") &&
                board[0][i].equals(board[1][i]) &&
                board[1][i].equals(board[2][i])) return true;
        }

        // Diagonals
        if (!board[0][0].equals("") &&
            board[0][0].equals(board[1][1]) &&
            board[1][1].equals(board[2][2])) return true;

        if (!board[0][2].equals("") &&
            board[0][2].equals(board[1][1]) &&
            board[1][1].equals(board[2][0])) return true;

        return false;
    }

    private boolean isBoardFull() {
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                if (buttons[i][j].getText().equals(""))
                    return false;
        return true;
    }

    private void disableButtons() {
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                buttons[i][j].setEnabled(false);
    }

    private void resetGame() {
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText("");
                buttons[i][j].setEnabled(true);
            }

        isXTurn = true;
        statusLabel.setText("Player X's Turn");
    }

    public static void main(String[] args) {
        new TicTacToe();
    }
}