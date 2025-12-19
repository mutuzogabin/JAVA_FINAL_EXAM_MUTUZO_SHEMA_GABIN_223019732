package security.ui;

import security.dao.CameraDAO;
import security.model.Camera;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class CameraMonitoringPanel extends JPanel {
    private final CameraDAO cameraDAO = new CameraDAO();
    private final DefaultTableModel tableModel;
    private final JTable table;

    public CameraMonitoringPanel() {
        setLayout(new BorderLayout(10, 10));

        tableModel = new DefaultTableModel(new Object[]{"Camera ID", "Location", "IP Address", "Status"}, 0);
        table = new JTable(tableModel);
        add(new JScrollPane(table), BorderLayout.CENTER);

        JButton refreshBtn = new JButton("Refresh");
        add(refreshBtn, BorderLayout.SOUTH);

        loadCameras();

        refreshBtn.addActionListener(e -> loadCameras());
    }

    private void loadCameras() {
        tableModel.setRowCount(0);
        List<Camera> cameras = cameraDAO.getAllCameras();
        for (Camera cam : cameras) {
            tableModel.addRow(new Object[]{
                    cam.getCameraID(), cam.getLocation(), cam.getIPAddress(), cam.getStatus()
            });
        }
    }
}
