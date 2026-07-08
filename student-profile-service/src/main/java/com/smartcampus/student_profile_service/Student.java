package com.smartcampus.student_profile_service;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Student {
    @Id
    private String id;
    private String name;
    private String email;
    private String status;

    // MANDATORY: Hibernate requires a no-argument constructor when using a database
    public Student() {}

    public Student(String id, String name, String email, String status) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.status = status;
    }

    // Keep all your Getters and Setters exactly the same as before...
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}