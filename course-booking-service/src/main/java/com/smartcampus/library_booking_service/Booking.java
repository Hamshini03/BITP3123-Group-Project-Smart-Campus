package com.smartcampus.library_booking_service;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Booking {
    @Id
    private String bookingId;
    private String studentId;
    private String isbn;
    private String bookTitle;
    private String status;

    public Booking() {}

    public Booking(String bookingId, String studentId, String isbn, String bookTitle, String status) {
        this.bookingId = bookingId;
        this.studentId = studentId;
        this.isbn = isbn;
        this.bookTitle = bookTitle;
        this.status = status;
    }

    // Getters and Setters
    public String getBookingId() { return bookingId; }
    public void setBookingId(String bookingId) { this.bookingId = bookingId; }
    public String getStudentId() { return studentId; }
    public void setStudentId(String studentId) { this.studentId = studentId; }
    public String getIsbn() { return isbn; }
    public void setIsbn(String isbn) { this.isbn = isbn; }
    public String getBookTitle() { return bookTitle; }
    public void setBookTitle(String bookTitle) { this.bookTitle = bookTitle; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}