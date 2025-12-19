package security.ui;

import security.model.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AdminDashboardFrame extends JFrame {
    private final User loggedUser;
    private final JPanel mainPanel;
    private final CardLayout cardLayout;

    public AdminDashboardFrame(User user) {
        this.loggedUser = user;
        setTitle("Admin Dashboard - SECURITY MONITORING MANAGEMENT SYSTEM");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        // Sidebar
        JPanel sidebar = new JPanel();
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));
        sidebar.setBackground(new Color(40, 45, 50));
        sidebar.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));

        // Create buttons
        JButton usersBtn = createSidebarButton("Manage Users");
        JButton eventsBtn = createSidebarButton("View Events");
        JButton cameraBtn = createSidebarButton("Camera Monitoring");
        JButton logoutBtn = createSidebarButton("Logout");

        // Add buttons with spacing
        sidebar.add(usersBtn);
        sidebar.add(Box.createVerticalStrut(10));
        sidebar.add(eventsBtn);
        sidebar.add(Box.createVerticalStrut(10));
        sidebar.add(cameraBtn);
        sidebar.add(Box.createVerticalGlue()); // push logout to bottom
        sidebar.add(logoutBtn);

        // Panels
        ManageUsersPanel manageUsersPanel = new ManageUsersPanel();
        ViewEventsPanel viewEventsPanel = new ViewEventsPanel();
        CameraMonitoringPanel cameraPanel = new CameraMonitoringPanel();

        mainPanel.add(manageUsersPanel, "USERS");
        mainPanel.add(viewEventsPanel, "EVENTS");
        mainPanel.add(cameraPanel, "CAMERA");

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(sidebar, BorderLayout.WEST);
        getContentPane().add(mainPanel, BorderLayout.CENTER);

        // Events
        usersBtn.addActionListener(e -> cardLayout.show(mainPanel, "USERS"));
        eventsBtn.addActionListener(e -> cardLayout.show(mainPanel, "EVENTS"));
        cameraBtn.addActionListener(e -> cardLayout.show(mainPanel, "CAMERA"));
        logoutBtn.addActionListener(e -> {
            dispose();
            new LoginFrame().setVisible(true);
        });
    }

    // Helper to create styled sidebar buttons
    private JButton createSidebarButton(String text) {
        JButton btn = new JButton(text);
        btn.setMaximumSize(new Dimension(180, 45));
        btn.setAlignmentX(Component.CENTER_ALIGNMENT);
        btn.setFocusPainted(false);
        btn.setForeground(Color.WHITE);
        btn.setBackground(new Color(60, 65, 70));
        btn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btn.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Rounded corners
        btn.setUI(new javax.swing.plaf.basic.BasicButtonUI() {
            @Override
            public void paint(Graphics g, JComponent c) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(btn.getBackground());
                g2.fillRoundRect(0, 0, btn.getWidth(), btn.getHeight(), 15, 15);
                g2.dispose();
                super.paint(g, c);
            }
        });

        // Hover effect
        btn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btn.setBackground(new Color(80, 85, 90));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btn.setBackground(new Color(60, 65, 70));
            }
        });

        return btn;
    }
}
