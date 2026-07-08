package com.smartcampus.course_enrolment_service;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Enrolment {
	@Id
	
	private String enrolmentId;
    private String studentId;
    private String courseCode;
    private String courseName;
    private String status;

    public Enrolment() {}

    public Enrolment(String enrolmentId, String studentId, String courseCode, String courseName, String status) {
        this.enrolmentId = enrolmentId;
        this.studentId = studentId;
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.status = status;
    }
    
 // Getters and Setters
    public String getEnrolmentId() { return enrolmentId; }
    public void setEnrolmentId(String enrolmentId) { this.enrolmentId = enrolmentId; }
    public String getStudentId() { return studentId; }
    public void setStudentId(String studentId) { this.studentId = studentId; }
    public String getCourseCode() { return courseCode; }
    public void setCourseCode(String courseCode) { this.courseCode = courseCode; }
    public String getCourseName() { return courseName; }
    public void setCourseName(String courseName) { this.courseName = courseName; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

}
