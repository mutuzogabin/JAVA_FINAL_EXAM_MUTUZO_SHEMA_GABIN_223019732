package security.ui;

import security.dao.UserDAO;
import security.model.User;

import javax.swing.*;
import java.awt.*;

public class SignupFrame extends JFrame {
    private JTextField usernameField, emailField, fullNameField;
    private JPasswordField passwordField, confirmPasswordField;
    private JComboBox<String> roleComboBox;

    public SignupFrame() {
        setTitle("User Registration - SECURITY MONITORING MANAGEMENT SYSTEM");
        setSize(450, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Main panel with padding
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        mainPanel.setBackground(new Color(245, 245, 245)); // light gray
        add(mainPanel);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // spacing
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Title
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        JLabel titleLabel = new JLabel("Sign Up", JLabel.CENTER);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        titleLabel.setForeground(new Color(50, 50, 50));
        mainPanel.add(titleLabel, gbc);

        gbc.gridwidth = 1; // reset for fields

        // Username
        gbc.gridy = 1;
        gbc.gridx = 0;
        mainPanel.add(new JLabel("Username:"), gbc);
        gbc.gridx = 1;
        usernameField = new JTextField();
        usernameField.setPreferredSize(new Dimension(200, 28));
        mainPanel.add(usernameField, gbc);

        // Email
        gbc.gridy = 2;
        gbc.gridx = 0;
        mainPanel.add(new JLabel("Email:"), gbc);
        gbc.gridx = 1;
        emailField = new JTextField();
        emailField.setPreferredSize(new Dimension(200, 28));
        mainPanel.add(emailField, gbc);

        // Full Name
        gbc.gridy = 3;
        gbc.gridx = 0;
        mainPanel.add(new JLabel("Full Name:"), gbc);
        gbc.gridx = 1;
        fullNameField = new JTextField();
        fullNameField.setPreferredSize(new Dimension(200, 28));
        mainPanel.add(fullNameField, gbc);

        // Role
        gbc.gridy = 4;
        gbc.gridx = 0;
        mainPanel.add(new JLabel("Role:"), gbc);
        gbc.gridx = 1;
        roleComboBox = new JComboBox<>(new String[]{"Security", "Viewer"});
        roleComboBox.setPreferredSize(new Dimension(200, 28));
        mainPanel.add(roleComboBox, gbc);

        // Password
        gbc.gridy = 5;
        gbc.gridx = 0;
        mainPanel.add(new JLabel("Password:"), gbc);
        gbc.gridx = 1;
        passwordField = new JPasswordField();
        passwordField.setPreferredSize(new Dimension(200, 28));
        mainPanel.add(passwordField, gbc);

        // Confirm Password
        gbc.gridy = 6;
        gbc.gridx = 0;
        mainPanel.add(new JLabel("Confirm Password:"), gbc);
        gbc.gridx = 1;
        confirmPasswordField = new JPasswordField();
        confirmPasswordField.setPreferredSize(new Dimension(200, 28));
        mainPanel.add(confirmPasswordField, gbc);

        // Buttons
        gbc.gridy = 7;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;

        JPanel buttonPanel = new JPanel(new GridBagLayout());
        buttonPanel.setBackground(new Color(245, 245, 245));
        GridBagConstraints btnGbc = new GridBagConstraints();
        btnGbc.insets = new Insets(5, 10, 5, 10);

        JButton registerButton = new JButton("Register");
        JButton backButton = new JButton("Back to Login");

        // Both buttons same "sky blue" color
        Color skyBlue = new Color(33, 150, 243);

        registerButton.setPreferredSize(new Dimension(120, 35));
        registerButton.setBackground(skyBlue);
        registerButton.setForeground(Color.WHITE);
        registerButton.setFocusPainted(false);

        backButton.setPreferredSize(new Dimension(120, 35));
        backButton.setBackground(skyBlue);
        backButton.setForeground(Color.WHITE);
        backButton.setFocusPainted(false);

        btnGbc.gridx = 0;
        buttonPanel.add(registerButton, btnGbc);
        btnGbc.gridx = 1;
        buttonPanel.add(backButton, btnGbc);

        mainPanel.add(buttonPanel, gbc);

        // Action Listeners
        registerButton.addActionListener(e -> registerUser());
        backButton.addActionListener(e -> {
            dispose();
            new LoginFrame().setVisible(true);
        });
    }

    private void registerUser() {
        String username = usernameField.getText().trim();
        String email = emailField.getText().trim();
        String fullName = fullNameField.getText().trim();
        String role = roleComboBox.getSelectedItem().toString();
        String password = new String(passwordField.getPassword());
        String confirm = new String(confirmPasswordField.getPassword());

        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Username and password are required!");
            return;
        }
        if (!password.equals(confirm)) {
            JOptionPane.showMessageDialog(this, "Passwords do not match!");
            return;
        }

        User u = new User();
        u.setUsername(username);
        u.setPassword(password);
        u.setEmail(email);
        u.setFullName(fullName);
        u.setRole(role);

        UserDAO dao = new UserDAO();
        if (dao.addUser(u)) {
            JOptionPane.showMessageDialog(this, "Registration successful!");
            dispose();
            new LoginFrame().setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Failed to register user. Username may already exist.");
        }
    }
}
