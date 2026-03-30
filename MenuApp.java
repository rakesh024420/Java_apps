import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Stack;

public class MenuApp extends JFrame implements ActionListener {

    JTextField display;
    String expression = "";

    public MenuApp() {

        setTitle("Apps MENU");
        setSize(700, 300);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(EXIT_ON_CLOSE);


        JPanel panel = new JPanel(new GridLayout(5, 3));

        String[] buttons = {
                "Calculator","Address Book","Analog Clock",
                "Currency Converter","Digital Clock","Guessing Game",
                "Image Viewer","Login App","Pizza Billing",
                "Quiz App","Simple Notepad","Stopwatch",
                "Tempreature Converter","Tic Tac Toe","To Do App"
        };

        for (String text : buttons) {
            JButton btn = new JButton(text);
            btn.setFont(new Font("Arial", Font.BOLD, 12));
            btn.addActionListener(this);
            panel.add(btn);
        }

        add(panel, BorderLayout.CENTER);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {

        String cmd = e.getActionCommand();

        if (cmd.equals("Calculator")) {
            new CalculatorApp();
        }
        else if (cmd.equals("Address Book")) {
            new AddressBookApp();
        }
        else if (cmd.equals("Analog Clock")) {
            JFrame frame = new JFrame("Analog Clock with Numbers");
            frame.add(new AnalogClock());
            frame.setSize(400, 400);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        }
        else if (cmd.equals("Currency Converter")) {
            new CurrencyConverter();
        }
        else if (cmd.equals("Digital Clock")) {
            new DigitalClock();
        }
        else if (cmd.equals("Guessing Game")) {
            new GuessingGame();
        }
        else if (cmd.equals("Image Viewer")) {
            new ImageViewerApp();
        }
        else if (cmd.equals("Login App")) {
            new LoginApp();
        }
        else if (cmd.equals("Pizza Billing")) {
            new PizzaBillingApp();
        }
        else if (cmd.equals("Quiz App")) {
            new QuizApp();
        }
        else if (cmd.equals("Simple Notepad")) {
            new SimpleNotepad();
        }
        else if (cmd.equals("Stopwatch")) {
            new StopwatchApp();
        }
        else if (cmd.equals("Tempreature Converter")) {
            new TempConverter();
        }
        else if (cmd.equals("Tic Tac Toe")) {
            new TicTacToe();
        }
        else
            new TodoApp();
    }

       public static void main(String[] args) {
        new MenuApp();
    }
}

