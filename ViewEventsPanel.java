package security.ui;

import security.dao.EventDAO;
import security.model.Event;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ViewEventsPanel extends JPanel {
    private final EventDAO eventDAO = new EventDAO();
    private final DefaultTableModel tableModel;
    private final JTable table;

    public ViewEventsPanel() {
        setLayout(new BorderLayout(10, 10));
        tableModel = new DefaultTableModel(new Object[]{"Event ID", "Camera ID", "Description", "Status", "Remarks", "Event Date"}, 0);
        table = new JTable(tableModel);

        add(new JScrollPane(table), BorderLayout.CENTER);

        JButton refreshBtn = new JButton("Refresh");
        add(refreshBtn, BorderLayout.SOUTH);

        loadEvents();

        refreshBtn.addActionListener(e -> loadEvents());
    }

    private void loadEvents() {
        tableModel.setRowCount(0);
        List<Event> events = eventDAO.getAllEvents();
        for (Event ev : events) {
            tableModel.addRow(new Object[]{
                    ev.getEventID(), ev.getCameraID(), ev.getDescription(), ev.getStatus(), ev.getRemarks(), ev.getEventDate()
            });
        }
    }
}
