package com.amigoscode.notification;

import com.amigoscode.clients.notification.NotificationRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/notifications")
@Slf4j
public record NotificationController(NotificationService service) {
    @PostMapping
    public void addNotification(@RequestBody NotificationRequest notificationRequest) {
        log.info("Sending new notification to: %s".formatted(notificationRequest.toCustomerEmail()));
        service.sendNotification(notificationRequest);
    }
}
