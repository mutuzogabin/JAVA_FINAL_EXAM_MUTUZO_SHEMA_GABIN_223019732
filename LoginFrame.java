package security.ui;

import security.dao.UserDAO;
import security.model.User;

import javax.swing.*;
import java.awt.*;

public class LoginFrame extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JCheckBox captchaCheckBox;

    public LoginFrame() {
        setTitle("SECURITY MONITORING MANAGEMENT SYSTEM");
        setSize(450, 320);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Main panel with padding and background color
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        mainPanel.setBackground(new Color(245, 245, 245)); // light gray
        add(mainPanel);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Title
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        JLabel titleLabel = new JLabel("LOGIN TO SECURITY SYSTEM", JLabel.CENTER);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        titleLabel.setForeground(new Color(50, 50, 50));
        mainPanel.add(titleLabel, gbc);

        // Username Label
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        mainPanel.add(new JLabel("Username:"), gbc);

        // Username Field
        gbc.gridx = 1;
        usernameField = new JTextField();
        usernameField.setPreferredSize(new Dimension(200, 28));
        mainPanel.add(usernameField, gbc);

        // Password Label
        gbc.gridx = 0;
        gbc.gridy = 2;
        mainPanel.add(new JLabel("Password:"), gbc);

        // Password Field
        gbc.gridx = 1;
        passwordField = new JPasswordField();
        passwordField.setPreferredSize(new Dimension(200, 28));
        mainPanel.add(passwordField, gbc);

        // CAPTCHA Checkbox
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        captchaCheckBox = new JCheckBox("Verify you're not a robot");
        captchaCheckBox.setBackground(new Color(245, 245, 245));
        mainPanel.add(captchaCheckBox, gbc);

        // Buttons Panel
        gbc.gridy = 4;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;

        JPanel buttonPanel = new JPanel(new GridBagLayout());
        buttonPanel.setBackground(new Color(245, 245, 245));
        GridBagConstraints btnGbc = new GridBagConstraints();
        btnGbc.insets = new Insets(5, 10, 5, 10);
        btnGbc.fill = GridBagConstraints.NONE;

        JButton loginButton = new JButton("Login");
        JButton signupButton = new JButton("Sign Up");

        // Rounded modern buttons
        loginButton.setPreferredSize(new Dimension(120, 35));
        loginButton.setBackground(new Color(33, 150, 243));
        loginButton.setForeground(Color.WHITE);
        loginButton.setFocusPainted(false);

        signupButton.setPreferredSize(new Dimension(120, 35));
        signupButton.setBackground(new Color(33, 150, 243));
        signupButton.setForeground(Color.WHITE);
        signupButton.setFocusPainted(false);

        // Place buttons side by side
        btnGbc.gridx = 0;
        buttonPanel.add(loginButton, btnGbc);
        btnGbc.gridx = 1;
        buttonPanel.add(signupButton, btnGbc);

        mainPanel.add(buttonPanel, gbc);

        // Action Listeners
        loginButton.addActionListener(e -> login());
        signupButton.addActionListener(e -> {
            dispose();
            new SignupFrame().setVisible(true);
        });
    }

    private void login() {
        String username = usernameField.getText().trim();
        String password = new String(passwordField.getPassword()).trim();

        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter username and password.", "Missing", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (!captchaCheckBox.isSelected()) {
            JOptionPane.showMessageDialog(this, "Please verify that you are not a robot.", "Verification Required", JOptionPane.WARNING_MESSAGE);
            return;
        }

        UserDAO dao = new UserDAO();
        User user = dao.getUserByCredentials(username, password);

        if (user == null) {
            JOptionPane.showMessageDialog(this, "Invalid username or password!", "Login Failed", JOptionPane.ERROR_MESSAGE);
            return;
        }

        dispose();
        String role = user.getRole();
        if ("Admin".equalsIgnoreCase(role)) new AdminDashboardFrame(user).setVisible(true);
        else if ("Security".equalsIgnoreCase(role)) new SecurityDashboardFrame(user).setVisible(true);
        else if ("Viewer".equalsIgnoreCase(role)) new ViewerDashboardFrame(user).setVisible(true);
        else JOptionPane.showMessageDialog(null, "Unknown role: " + role);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginFrame().setVisible(true));
    }
}
