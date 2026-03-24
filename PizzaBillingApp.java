import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PizzaBillingApp extends JFrame implements ActionListener {

    JCheckBox pizza, burger, pasta, fries;
    JTextField qtyPizza, qtyBurger, qtyPasta, qtyFries;
    JButton calculateBtn, resetBtn;
    JTextArea billArea;

    // Prices
    int pricePizza = 200;
    int priceBurger = 120;
    int pricePasta = 150;
    int priceFries = 80;

    public PizzaBillingApp() {

        setTitle("Food Billing System");
        setSize(600, 500);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JLabel title = new JLabel("Pizza / Food Order Billing");
        title.setBounds(180, 10, 250, 30);
        title.setFont(new Font("Arial", Font.BOLD, 18));
        add(title);

        // Items
        pizza = new JCheckBox("Pizza (₹200)");
        burger = new JCheckBox("Burger (₹120)");
        pasta = new JCheckBox("Pasta (₹150)");
        fries = new JCheckBox("Fries (₹80)");

        pizza.setBounds(50, 60, 150, 25);
        burger.setBounds(50, 100, 150, 25);
        pasta.setBounds(50, 140, 150, 25);
        fries.setBounds(50, 180, 150, 25);

        add(pizza);
        add(burger);
        add(pasta);
        add(fries);

        // Quantity fields
        qtyPizza = new JTextField("0");
        qtyBurger = new JTextField("0");
        qtyPasta = new JTextField("0");
        qtyFries = new JTextField("0");

        qtyPizza.setBounds(220, 60, 50, 25);
        qtyBurger.setBounds(220, 100, 50, 25);
        qtyPasta.setBounds(220, 140, 50, 25);
        qtyFries.setBounds(220, 180, 50, 25);

        add(qtyPizza);
        add(qtyBurger);
        add(qtyPasta);
        add(qtyFries);

        // Buttons
        calculateBtn = new JButton("Calculate Bill");
        resetBtn = new JButton("Reset");

        calculateBtn.setBounds(50, 230, 150, 30);
        resetBtn.setBounds(220, 230, 100, 30);

        add(calculateBtn);
        add(resetBtn);

        calculateBtn.addActionListener(this);
        resetBtn.addActionListener(this);

        // Bill area
        billArea = new JTextArea();
        billArea.setBounds(50, 280, 450, 150);
        billArea.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        add(billArea);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == calculateBtn) {
            int total = 0;
            StringBuilder bill = new StringBuilder();
            bill.append("------ FOOD BILL ------\n");

            try {
                if (pizza.isSelected()) {
                    int qty = Integer.parseInt(qtyPizza.getText());
                    int cost = qty * pricePizza;
                    total += cost;
                    bill.append("Pizza x ").append(qty).append(" = ₹").append(cost).append("\n");
                }

                if (burger.isSelected()) {
                    int qty = Integer.parseInt(qtyBurger.getText());
                    int cost = qty * priceBurger;
                    total += cost;
                    bill.append("Burger x ").append(qty).append(" = ₹").append(cost).append("\n");
                }

                if (pasta.isSelected()) {
                    int qty = Integer.parseInt(qtyPasta.getText());
                    int cost = qty * pricePasta;
                    total += cost;
                    bill.append("Pasta x ").append(qty).append(" = ₹").append(cost).append("\n");
                }

                if (fries.isSelected()) {
                    int qty = Integer.parseInt(qtyFries.getText());
                    int cost = qty * priceFries;
                    total += cost;
                    bill.append("Fries x ").append(qty).append(" = ₹").append(cost).append("\n");
                }

                bill.append("----------------------\n");
                bill.append("Total = ₹").append(total);

                billArea.setText(bill.toString());

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Enter valid quantities!");
            }
        }

        if (e.getSource() == resetBtn) {
            pizza.setSelected(false);
            burger.setSelected(false);
            pasta.setSelected(false);
            fries.setSelected(false);

            qtyPizza.setText("0");
            qtyBurger.setText("0");
            qtyPasta.setText("0");
            qtyFries.setText("0");

            billArea.setText("");
        }
    }

    public static void main(String[] args) {
        new PizzaBillingApp();
    }
}