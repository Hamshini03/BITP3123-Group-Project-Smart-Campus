package com.smartcampus.library_booking_service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.List;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    @Autowired
    private BookingRepository bookingRepository;

    private final RestTemplate restTemplate = new RestTemplate();
    private final ExecutorService threadPool = Executors.newFixedThreadPool(5);

    @PostMapping
    public String createBooking(@RequestBody Booking incomingData) {
                               
        String bookingId = UUID.randomUUID().toString().substring(0, 8);
        
        // Save initially as PROCESSING
        Booking booking = new Booking(
            bookingId, 
            incomingData.getStudentId(), 
            incomingData.getIsbn(), 
            incomingData.getBookTitle(), 
            "PROCESSING"
        );
        bookingRepository.save(booking);

        // Background worker pool checks the Student Profile Service and sends events asynchronously
        threadPool.submit(() -> {
            String finalStatus = "FAILED"; // Track status state for notification rule
            try {
                String studentProfileUrl = "http://localhost:8081/api/students/" + incomingData.getStudentId();
                Object student = restTemplate.getForObject(studentProfileUrl, Object.class);

                if (student != null) {
                    booking.setStatus("RESERVED");
                    finalStatus = "RESERVED";
                } else {
                    booking.setStatus("FAILED - INVALID STUDENT");
                }
            } catch (Exception e) {
                booking.setStatus("FAILED - PROFILE SERVICE DOWN");
            }
            
            // Updates database automatically via the generated primary key
            bookingRepository.save(booking);

            // ==========================================
            // NEW CHANGED PART: Asynchronous event fired to port 8084
            // ==========================================
            if ("RESERVED".equals(finalStatus)) {
                try {
                    java.util.Map<String, String> alertPayload = new java.util.HashMap<>();
                    alertPayload.put("studentId", booking.getStudentId());
                    alertPayload.put("message", "Book Reserved: " + booking.getBookTitle() + " is ready for pickup!");
                    alertPayload.put("type", "SMS_ALERT");
                    
                    // Asynchronously fires the data container onto port 8084
                    restTemplate.postForObject("http://localhost:8084/api/notifications", alertPayload, String.class);
                } catch (Exception e) {
                    System.out.println("Notification server unreachable. Event logged locally.");
                }
            }
            // ==========================================
        });

        return "Booking request received! Booking Tracking ID: " + bookingId;
    }
    
    @GetMapping("/{id}")
    public Booking getBookingStatus(@PathVariable String id) {
        return bookingRepository.findById(id).orElse(null);
    }

    @GetMapping
    public java.util.List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }
}