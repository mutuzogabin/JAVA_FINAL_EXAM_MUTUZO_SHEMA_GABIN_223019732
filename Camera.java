package security.model;

public class Camera {
    private int cameraID;
    private String location;
    private String ipAddress;
    private String status;

    public int getCameraID() { return cameraID; }
    public void setCameraID(int cameraID) { this.cameraID = cameraID; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public String getIPAddress() { return ipAddress; }
    public void setIPAddress(String ipAddress) { this.ipAddress = ipAddress; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
