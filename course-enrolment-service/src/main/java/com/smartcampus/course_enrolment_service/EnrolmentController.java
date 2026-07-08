package com.smartcampus.course_enrolment_service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RestController
@RequestMapping("/api/enrolments")
public class EnrolmentController {

    @Autowired
    private EnrolmentRepository enrolmentRepository;

    private final RestTemplate restTemplate = new RestTemplate();
    private final ExecutorService threadPool = Executors.newFixedThreadPool(5);

    @PostMapping
    public String enrolStudent(@RequestBody Enrolment incomingData) { 
                               
        String enrolmentId = UUID.randomUUID().toString().substring(0, 8);
        
        Enrolment enrolment = new Enrolment(
            enrolmentId, 
            incomingData.getStudentId(), 
            incomingData.getCourseCode(), 
            incomingData.getCourseName(), 
            "PROCESSING"
        );
        enrolmentRepository.save(enrolment);

        // Background worker thread handles validation and notification dispatch asynchronously
        threadPool.submit(() -> {
            String finalStatus = "FAILED"; // Track status state for notification rule
            try {
                String studentProfileUrl = "http://localhost:8081/api/students/" + incomingData.getStudentId();
                Object student = restTemplate.getForObject(studentProfileUrl, Object.class);

                if (student != null) {
                    enrolment.setStatus("CONFIRMED");
                    finalStatus = "CONFIRMED";
                } else {
                    enrolment.setStatus("FAILED - STUDENT NOT FOUND");
                }
            } catch (Exception e) {
                enrolment.setStatus("FAILED - PROFILE SERVICE DOWN");
            }
            
            // Save final enrollment status to database
            enrolmentRepository.save(enrolment);

            // ==========================================
            // NEW CHANGED PART: Asynchronous event fired to port 8084
            // ==========================================
            if ("CONFIRMED".equals(finalStatus)) {
                try {
                    java.util.Map<String, String> alertPayload = new java.util.HashMap<>();
                    alertPayload.put("studentId", enrolment.getStudentId());
                    alertPayload.put("message", "Success! You have been enrolled in " + enrolment.getCourseName());
                    alertPayload.put("type", "EMAIL_ALERT");
                    
                    // Asynchronously drops the data container onto port 8084
                    restTemplate.postForObject("http://localhost:8084/api/notifications", alertPayload, String.class);
                } catch (Exception e) {
                    System.out.println("Notification server unreachable. Event logged locally.");
                }
            }
            // ==========================================
        });

        return "Enrolment request received! Tracking ID: " + enrolmentId;
    }
    
    @GetMapping("/{id}")
    public Enrolment getEnrolmentStatus(@PathVariable String id) {
        return enrolmentRepository.findById(id).orElse(null);
    }
    
 // 3. READ all enrolments list
    @GetMapping
    public java.util.List<Enrolment> getAllEnrolments() {
        return enrolmentRepository.findAll();
    }
    
 // 4. DELETE an enrolment by ID
    @DeleteMapping("/{id}")
    public String deleteEnrolment(@PathVariable String id) {
        if (enrolmentRepository.existsById(id)) {
            enrolmentRepository.deleteById(id);
            return "Enrolment record " + id + " deleted successfully!";
        } else {
            return "Enrolment ID " + id + " not found.";
        }
    }
}