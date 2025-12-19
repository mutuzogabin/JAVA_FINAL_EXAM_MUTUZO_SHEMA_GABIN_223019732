package security.ui;

import security.model.User;
import javax.swing.*;
import java.awt.*;

public class DashboardFrame extends JFrame {

    private User user;

    public DashboardFrame(User user) {
        this.user = user;
        setTitle("Security Monitoring System - " + user.getRole() + " Dashboard");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Main panel
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        JLabel welcomeLabel = new JLabel("Welcome, " + user.getFullName() + " (" + user.getRole() + ")");
        welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 18));
        mainPanel.add(welcomeLabel, BorderLayout.NORTH);

        // Role-specific panel
        JPanel contentPanel = getRolePanel(user.getRole());
        mainPanel.add(contentPanel, BorderLayout.CENTER);

        // Logout button at the bottom
        JButton logoutButton = new JButton("Logout");
        logoutButton.addActionListener(e -> logout());
        JPanel bottomPanel = new JPanel();
        bottomPanel.add(logoutButton);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        add(mainPanel);
        setVisible(true);
    }

    private JPanel getRolePanel(String role) {
        JPanel panel = new JPanel(new BorderLayout());

        switch (role.toLowerCase()) {
            case "admin":
                // Buttons panel on top
                JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
                JButton manageUsersBtn = new JButton("Manage Users");
                JButton viewLogsBtn = new JButton("View All Logs");
                JButton systemSettingsBtn = new JButton("System Settings");
                buttonPanel.add(manageUsersBtn);
                buttonPanel.add(viewLogsBtn);
                buttonPanel.add(systemSettingsBtn);

                panel.add(buttonPanel, BorderLayout.NORTH);

                // Content panel fills the center
                JPanel contentPanel = new JPanel(new BorderLayout());
                panel.add(contentPanel, BorderLayout.CENTER);

                // Default: show ManageUsersPanel full screen
                contentPanel.add(new ManageUsersPanel(), BorderLayout.CENTER);

                // Button actions
                manageUsersBtn.addActionListener(e -> {
                    contentPanel.removeAll();
                    contentPanel.add(new ManageUsersPanel(), BorderLayout.CENTER);
                    contentPanel.revalidate();
                    contentPanel.repaint();
                });

                viewLogsBtn.addActionListener(e -> {
                    contentPanel.removeAll();
                    contentPanel.add(new ViewLogsPanel(), BorderLayout.CENTER);
                    contentPanel.revalidate();
                    contentPanel.repaint();
                });

                systemSettingsBtn.addActionListener(e -> {
                    contentPanel.removeAll();
                    contentPanel.add(new SystemSettingsPanel(), BorderLayout.CENTER);
                    contentPanel.revalidate();
                    contentPanel.repaint();
                });

                break;

            case "security":
                JPanel securityPanel = new JPanel(new GridLayout(0, 1, 10, 10));
                securityPanel.add(new JButton("View Active Alerts"));
                securityPanel.add(new JButton("Record New Event"));
                securityPanel.add(new JButton("View Camera Feed"));
                panel.add(securityPanel, BorderLayout.CENTER);
                break;

            case "viewer":
                JPanel viewerPanel = new JPanel(new GridLayout(0, 1, 10, 10));
                viewerPanel.add(new JButton("View Reports"));
                viewerPanel.add(new JButton("Read Only Logs"));
                panel.add(viewerPanel, BorderLayout.CENTER);
                break;

            default:
                panel.add(new JLabel("No dashboard found for this role."), BorderLayout.CENTER);
        }

        return panel;
    }

    // Logout method
    private void logout() {
        int confirm = JOptionPane.showConfirmDialog(this,
                "Are you sure you want to logout?", "Logout Confirmation",
                JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            dispose(); // close dashboard
            new LoginFrame().setVisible(true); // open login screen
        }
    }
}
