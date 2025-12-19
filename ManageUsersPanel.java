package security.ui;

import security.dao.UserDAO;
import security.model.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ManageUsersPanel extends JPanel {
    private final UserDAO userDAO = new UserDAO();
    private final DefaultTableModel tableModel;
    private final JTable userTable;

    public ManageUsersPanel() {
        setLayout(new BorderLayout(10, 10));

        // Table setup
        tableModel = new DefaultTableModel(new Object[]{"User ID", "Username", "Role"}, 0);
        userTable = new JTable(tableModel);
        add(new JScrollPane(userTable), BorderLayout.CENTER);

        // Buttons
        JPanel btnPanel = new JPanel();
        JButton addBtn = new JButton("Add User");
        JButton editBtn = new JButton("Edit User");
        JButton deleteBtn = new JButton("Delete User");
        btnPanel.add(addBtn);
        btnPanel.add(editBtn);
        btnPanel.add(deleteBtn);

        add(btnPanel, BorderLayout.SOUTH);

        loadUsers();

        // Add User
        addBtn.addActionListener(e -> {
            String username = JOptionPane.showInputDialog(this, "Enter Username:");
            String password = JOptionPane.showInputDialog(this, "Enter Password:");
            String role = JOptionPane.showInputDialog(this, "Enter Role (Admin/Security/Viewer):");

            if (username != null && password != null && role != null) {
                User user = new User();
                user.setUsername(username);
                user.setPassword(password); // must set password here
                user.setRole(role);
                if (userDAO.addUser(user)) {
                    JOptionPane.showMessageDialog(this, "User added successfully.");
                    loadUsers();
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to add user.");
                }
            }
        });

        // Edit User
        editBtn.addActionListener(e -> {
            int row = userTable.getSelectedRow();
            if (row >= 0) {
                int id = (int) tableModel.getValueAt(row, 0);
                String currentUsername = (String) tableModel.getValueAt(row, 1);
                String currentRole = (String) tableModel.getValueAt(row, 2);

                String username = JOptionPane.showInputDialog(this, "New Username:", currentUsername);
                String role = JOptionPane.showInputDialog(this, "New Role:", currentRole);
                String password = JOptionPane.showInputDialog(this, "New Password (leave blank to keep current):");

                if (username != null && role != null) {
                    // Fetch existing user to keep current password if not changed
                    User user = userDAO.getUserByID(id);
                    if (user == null) {
                        JOptionPane.showMessageDialog(this, "User not found!");
                        return;
                    }

                    user.setUsername(username);
                    user.setRole(role);
                    if (password != null && !password.isEmpty()) {
                        user.setPassword(password); // update only if not empty
                    }

                    if (userDAO.updateUser(user)) {
                        JOptionPane.showMessageDialog(this, "User updated successfully.");
                        loadUsers();
                    } else {
                        JOptionPane.showMessageDialog(this, "Failed to update user.");
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "Select a user to edit.");
            }
        });

        // Delete User
        deleteBtn.addActionListener(e -> {
            int row = userTable.getSelectedRow();
            if (row >= 0) {
                int id = (int) tableModel.getValueAt(row, 0);
                if (userDAO.deleteUser(id)) {
                    JOptionPane.showMessageDialog(this, "User deleted successfully.");
                    loadUsers();
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to delete user.");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Select a user to delete.");
            }
        });
    }

    private void loadUsers() {
        tableModel.setRowCount(0);
        List<User> users = userDAO.getAllUsers();
        for (User u : users) {
            tableModel.addRow(new Object[]{u.getUserID(), u.getUsername(), u.getRole()});
        }
    }
}
