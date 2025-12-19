package security.dao;

import security.model.Camera;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CameraDAO {
    private final String URL = "jdbc:mysql://127.0.0.1/securitydb";
    private final String USER = "root";
    private final String PASSWORD = "";

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public List<Camera> getAllCameras() {
        List<Camera> list = new ArrayList<>();
        String sql = "SELECT * FROM cameras ORDER BY CameraID";
        try (Connection conn = getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Camera c = new Camera();
                c.setCameraID(rs.getInt("CameraID"));
                c.setLocation(rs.getString("Location"));
                c.setIPAddress(rs.getString("IPAddress"));
                c.setStatus(rs.getString("Status"));
                list.add(c);
            }
        } catch (SQLException ex) { ex.printStackTrace(); }
        return list;
    }

    public boolean updateCameraStatus(int cameraID, String status) {
        String sql = "UPDATE cameras SET Status=? WHERE CameraID=?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, status);
            ps.setInt(2, cameraID);
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) { ex.printStackTrace(); return false; }
    }
}
