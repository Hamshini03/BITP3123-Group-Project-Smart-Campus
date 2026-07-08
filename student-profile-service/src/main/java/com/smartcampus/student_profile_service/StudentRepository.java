package com.smartcampus.student_profile_service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, String> {
    // No code needed inside! JpaRepository handles all basic CRUD operations automatically.
}