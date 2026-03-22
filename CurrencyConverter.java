import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;

public class CurrencyConverter extends JFrame implements ActionListener {

    private JTextField amountField;
    private JComboBox<String> fromCurrency, toCurrency;
    private JLabel resultLabel;
    private JButton convertButton;

    // Fixed exchange rates (relative to USD)
    private HashMap<String, Double> rates;

    public CurrencyConverter() {
        setTitle("Currency Converter");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(5, 2, 10, 10));

        // Initialize rates
        rates = new HashMap<>();
        rates.put("USD", 1.0);
        rates.put("INR", 94.01);
        rates.put("EUR", 0.86);
        rates.put("GBP", 0.75);
        rates.put("JPY", 159.24);

        // Components
        amountField = new JTextField();

        String[] currencies = {"USD", "INR", "EUR", "GBP", "JPY"};
        fromCurrency = new JComboBox<>(currencies);
        toCurrency = new JComboBox<>(currencies);

        convertButton = new JButton("Convert");
        resultLabel = new JLabel("Result: ", JLabel.CENTER);

        convertButton.addActionListener(this);

        // Add components
        add(new JLabel("Enter Amount:"));
        add(amountField);
        add(new JLabel("From:"));
        add(fromCurrency);
        add(new JLabel("To:"));
        add(toCurrency);
        add(new JLabel());
        add(convertButton);
        add(resultLabel);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            double amount = Double.parseDouble(amountField.getText());

            String from = (String) fromCurrency.getSelectedItem();
            String to = (String) toCurrency.getSelectedItem();

            // Convert via USD base
            double usdAmount = amount / rates.get(from);
            double converted = usdAmount * rates.get(to);

            resultLabel.setText("Result: " + String.format("%.2f", converted) + " " + to);

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Enter a valid number!");
        }
    }

    public static void main(String[] args) {
        new CurrencyConverter();
    }
}