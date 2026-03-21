import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoginApp extends JFrame implements ActionListener {

    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;

    public LoginApp() {
        setTitle("Login Form");
        setSize(350, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 2, 10, 10));

        // Username
        JLabel userLabel = new JLabel("Username:");
        usernameField = new JTextField();

        // Password
        JLabel passLabel = new JLabel("Password:");
        passwordField = new JPasswordField();

        // Button
        loginButton = new JButton("Login");
        loginButton.addActionListener(this);

        // Add components
        add(userLabel);
        add(usernameField);
        add(passLabel);
        add(passwordField);
        add(new JLabel()); // empty space
        add(loginButton);

        setLocationRelativeTo(null); // center screen
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        // Dummy credentials
        if (username.equals("admin") && password.equals("1234")) {
            JOptionPane.showMessageDialog(this, "Login Successful!");
        } else {
            JOptionPane.showMessageDialog(this, "Invalid Username or Password");
        }
    }

    public static void main(String[] args) {
        new LoginApp();
    }
}