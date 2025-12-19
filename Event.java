package security.model;

import java.sql.Timestamp;

public class Event {
    private int eventID;
    private int cameraID;
    private String description;
    private String status;
    private String remarks;
    private Timestamp eventDate;

    public int getEventID() { return eventID; }
    public void setEventID(int eventID) { this.eventID = eventID; }

    public int getCameraID() { return cameraID; }
    public void setCameraID(int cameraID) { this.cameraID = cameraID; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getRemarks() { return remarks; }
    public void setRemarks(String remarks) { this.remarks = remarks; }

    public Timestamp getEventDate() { return eventDate; }
    public void setEventDate(Timestamp eventDate) { this.eventDate = eventDate; }
}
