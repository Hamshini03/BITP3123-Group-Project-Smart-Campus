package com.smartcampus.notification_service;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Notification {
	@Id
    private String notificationId;
    private String studentId;
    private String message;
    private String type; // e.g., "EMAIL" or "SMS"

    public Notification() {}

    public Notification(String notificationId, String studentId, String message, String type) {
        this.notificationId = notificationId;
        this.studentId = studentId;
        this.message = message;
        this.type = type;
    }

    // Getters and Setters
    public String getNotificationId() { return notificationId; }
    public void setNotificationId(String notificationId) { this.notificationId = notificationId; }
    public String getStudentId() { return studentId; }
    public void setStudentId(String studentId) { this.studentId = studentId; }
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

}
