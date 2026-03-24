import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;

public class AddressBookApp extends JFrame implements ActionListener {

    JTextField nameField, phoneField, emailField;
    JButton addBtn, updateBtn, deleteBtn, clearBtn;
    JTable table;
    DefaultTableModel model;

    int selectedRow = -1;

    public AddressBookApp() {

        setTitle("Contact / Address Book");
        setSize(800, 400);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JLabel title = new JLabel("Address Book");
        title.setFont(new Font("Arial", Font.BOLD, 20));
        title.setBounds(250, 10, 200, 30);
        add(title);

        // Labels
        JLabel nameLabel = new JLabel("Name:");
        JLabel phoneLabel = new JLabel("Phone:");
        JLabel emailLabel = new JLabel("Email:");

        nameLabel.setBounds(50, 60, 100, 25);
        phoneLabel.setBounds(50, 100, 100, 25);
        emailLabel.setBounds(50, 140, 100, 25);

        add(nameLabel);
        add(phoneLabel);
        add(emailLabel);

        // Text fields
        nameField = new JTextField();
        phoneField = new JTextField();
        emailField = new JTextField();

        nameField.setBounds(120, 60, 200, 25);
        phoneField.setBounds(120, 100, 200, 25);
        emailField.setBounds(120, 140, 200, 25);

        add(nameField);
        add(phoneField);
        add(emailField);

        // Buttons
        addBtn = new JButton("Add");
        updateBtn = new JButton("Update");
        deleteBtn = new JButton("Delete");
        clearBtn = new JButton("Clear");

        addBtn.setBounds(50, 180, 80, 30);
        updateBtn.setBounds(140, 180, 90, 30);
        deleteBtn.setBounds(240, 180, 90, 30);
        clearBtn.setBounds(340, 180, 80, 30);

        add(addBtn);
        add(updateBtn);
        add(deleteBtn);
        add(clearBtn);

        addBtn.addActionListener(this);
        updateBtn.addActionListener(this);
        deleteBtn.addActionListener(this);
        clearBtn.addActionListener(this);

        // Table
        model = new DefaultTableModel();
        model.addColumn("Name");
        model.addColumn("Phone");
        model.addColumn("Email");

        table = new JTable(model);
        JScrollPane sp = new JScrollPane(table);
        sp.setBounds(450, 60, 300, 200);
        add(sp);

        // Row selection event
        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                selectedRow = table.getSelectedRow();
                nameField.setText(model.getValueAt(selectedRow, 0).toString());
                phoneField.setText(model.getValueAt(selectedRow, 1).toString());
                emailField.setText(model.getValueAt(selectedRow, 2).toString());
            }
        });

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {

        // ADD
        if (e.getSource() == addBtn) {
            String name = nameField.getText();
            String phone = phoneField.getText();
            String email = emailField.getText();

            if (name.isEmpty() || phone.isEmpty() || email.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill all fields!");
                return;
            }

            model.addRow(new Object[]{name, phone, email});
            clearFields();
        }

        // UPDATE
        if (e.getSource() == updateBtn) {
            if (selectedRow >= 0) {
                model.setValueAt(nameField.getText(), selectedRow, 0);
                model.setValueAt(phoneField.getText(), selectedRow, 1);
                model.setValueAt(emailField.getText(), selectedRow, 2);
                clearFields();
            } else {
                JOptionPane.showMessageDialog(this, "Select a contact to update!");
            }
        }

        // DELETE
        if (e.getSource() == deleteBtn) {
            if (selectedRow >= 0) {
                model.removeRow(selectedRow);
                clearFields();
            } else {
                JOptionPane.showMessageDialog(this, "Select a contact to delete!");
            }
        }

        // CLEAR
        if (e.getSource() == clearBtn) {
            clearFields();
        }
    }

    void clearFields() {
        nameField.setText("");
        phoneField.setText("");
        emailField.setText("");
        selectedRow = -1;
        table.clearSelection();
    }

    public static void main(String[] args) {
        new AddressBookApp();
    }
}