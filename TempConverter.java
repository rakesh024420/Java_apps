import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TempConverter extends JFrame implements ActionListener {

    private JTextField inputField;
    private JLabel resultLabel;
    private JButton cToFButton, fToCButton;

    public TempConverter() {
        setTitle("Temperature Converter");
        setSize(350, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(4, 1, 10, 10));

        inputField = new JTextField();
        resultLabel = new JLabel("Result: ", JLabel.CENTER);

        cToFButton = new JButton("Celsius → Fahrenheit");
        fToCButton = new JButton("Fahrenheit → Celsius");

        cToFButton.addActionListener(this);
        fToCButton.addActionListener(this);

        add(new JLabel("Enter Temperature:", JLabel.CENTER));
        add(inputField);
        add(cToFButton);
        add(fToCButton);
        add(resultLabel);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            double temp = Double.parseDouble(inputField.getText());

            if (e.getSource() == cToFButton) {
                double result = (temp * 9 / 5) + 32;
                resultLabel.setText("Result: " + result + " °F");
            } else if (e.getSource() == fToCButton) {
                double result = (temp - 32) * 5 / 9;
                resultLabel.setText("Result: " + result + " °C");
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Enter a valid number!");
        }
    }

    public static void main(String[] args) {
        new TempConverter();
    }
}