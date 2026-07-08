package com.smartcampus.notification_service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {
	
	@Autowired
    private NotificationRepository notificationRepository;

    @PostMapping
    public void receiveSystemEvent(@RequestBody Notification incomingData) {
        String notificationId = UUID.randomUUID().toString().substring(0, 8);
        
        Notification notification = new Notification(
            notificationId,
            incomingData.getStudentId(),
            incomingData.getMessage(),
            incomingData.getType()
        );
        
        notificationRepository.save(notification);
        
        // This prints automatically to your console when an event is consumed!
        System.out.println("\n--- 🔔 ASYNCHRONOUS EVENT CONSUMED ---");
        System.out.println("Alert Type: " + notification.getType());
        System.out.println("To Student ID: " + notification.getStudentId());
        System.out.println("Message: " + notification.getMessage());
        System.out.println("---------------------------------------\n");
    }

}
