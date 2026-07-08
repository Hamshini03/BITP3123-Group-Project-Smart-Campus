package com.smartcampus.student_profile_service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

    // 1. CREATE a student
    @PostMapping
    public String createStudent(@RequestBody Student student) {
        studentRepository.save(student);
        return "Student saved to database successfully!";
    }

    // 2. READ a student by ID
    @GetMapping("/{id}")
    public Student getStudentById(@PathVariable String id) {
        Optional<Student> student = studentRepository.findById(id);
        return student.orElse(null); // Returns student if found, or null if not found
    }

    // 3. UPDATE a student
    @PutMapping("/{id}")
    public String updateStudent(@PathVariable String id, @RequestBody Student updatedStudent) {
        if (studentRepository.existsById(id)) {
            updatedStudent.setId(id); // Ensure the ID matches
            studentRepository.save(updatedStudent); // .save acts as update if ID exists
            return "Student updated in database successfully!";
        }
        return "Student not found!";
    }

    // 4. DELETE a student
    @DeleteMapping("/{id}")
    public String deleteStudent(@PathVariable String id) {
        if (studentRepository.existsById(id)) {
            studentRepository.deleteById(id);
            return "Student deleted from database successfully!";
        }
        return "Student not found!";
    }
    
 // 5. READ all students list
    @GetMapping
    public java.util.List<Student> getAllStudents() {
        return studentRepository.findAll();
    }
}