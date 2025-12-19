package security.ui;

import security.dao.EventDAO;
import security.model.Event;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class UpdateEventStatusPanel extends JPanel {
    private JTable table;
    private DefaultTableModel model;
    private final EventDAO dao = new EventDAO();

    public UpdateEventStatusPanel() {
        setLayout(new BorderLayout(10,10));
        model = new DefaultTableModel(new Object[]{"EventID","CameraID","Description","Status","Remarks","Date"}, 0);
        table = new JTable(model);
        add(new JScrollPane(table), BorderLayout.CENTER);

        JButton updateBtn = new JButton("Update Status");
        add(updateBtn, BorderLayout.SOUTH);
        loadEvents();

        updateBtn.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row >= 0) {
                int eventID = (int) model.getValueAt(row, 0);
                String newStatus = JOptionPane.showInputDialog(this, "Enter new status:");
                if (newStatus != null && !newStatus.trim().isEmpty()) {
                    String remarks = JOptionPane.showInputDialog(this, "Remarks:");
                    dao.updateEventStatus(eventID, newStatus, remarks);
                    loadEvents();
                }
            } else {
                JOptionPane.showMessageDialog(this, "Select an event to update.");
            }
        });
    }

    private void loadEvents() {
        model.setRowCount(0);
        List<Event> events = dao.getAllEvents();
        for (Event e : events) {
            model.addRow(new Object[]{
                    e.getEventID(), e.getCameraID(), e.getDescription(), e.getStatus(), e.getRemarks(), e.getEventDate()
            });
        }
    }
}
