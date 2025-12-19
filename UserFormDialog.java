package security.ui;

import security.dao.UserDAO;
import security.model.User;

import javax.swing.*;
import java.awt.*;

public class UserFormDialog extends JDialog {
    private JTextField usernameField, emailField, fullNameField;
    private JPasswordField passwordField;
    private JComboBox<String> roleComboBox;
    private JButton saveButton, cancelButton;

    private final UserDAO dao = new UserDAO();
    private final User editingUser; // null => add

    public UserFormDialog(JFrame parent, User user) {
        super(parent, true);
        this.editingUser = user;
        setTitle(user == null ? "Add User" : "Edit User");
        setSize(400, 300);
        setLocationRelativeTo(parent);
        setLayout(new GridLayout(6,2,10,10));

        add(new JLabel("Username:")); usernameField = new JTextField(); add(usernameField);
        add(new JLabel("Email:")); emailField = new JTextField(); add(emailField);
        add(new JLabel("Full Name:")); fullNameField = new JTextField(); add(fullNameField);
        add(new JLabel("Role:")); roleComboBox = new JComboBox<>(new String[]{"Security","Viewer"}); add(roleComboBox);
        add(new JLabel("Password:")); passwordField = new JPasswordField(); add(passwordField);
        saveButton = new JButton("Save"); cancelButton = new JButton("Cancel"); add(saveButton); add(cancelButton);

        if (editingUser != null) {
            usernameField.setText(editingUser.getUsername());
            emailField.setText(editingUser.getEmail());
            fullNameField.setText(editingUser.getFullName());
            roleComboBox.setSelectedItem(editingUser.getRole());
        }

        saveButton.addActionListener(e -> saveUser());
        cancelButton.addActionListener(e -> dispose());
    }

    private void saveUser() {
        String username = usernameField.getText().trim();
        String email = emailField.getText().trim();
        String fullName = fullNameField.getText().trim();
        String role = roleComboBox.getSelectedItem().toString();
        String pw = new String(passwordField.getPassword()).trim();

        if (username.isEmpty() || fullName.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Username and full name required!");
            return;
        }

        if (editingUser == null) {
            User u = new User();
            u.setUsername(username);
            u.setPassword(pw); // plain
            u.setEmail(email);
            u.setFullName(fullName);
            u.setRole(role);
            if (dao.addUser(u)) {
                JOptionPane.showMessageDialog(this, "User added.");
                dispose();
            } else JOptionPane.showMessageDialog(this, "Failed to add user.");
        } else {
            editingUser.setUsername(username);
            if (!pw.isEmpty()) editingUser.setPassword(pw);
            editingUser.setEmail(email);
            editingUser.setFullName(fullName);
            editingUser.setRole(role);
            if (dao.updateUser(editingUser)) {
                JOptionPane.showMessageDialog(this, "User updated.");
                dispose();
            } else JOptionPane.showMessageDialog(this, "Failed to update user.");
        }
    }
}
