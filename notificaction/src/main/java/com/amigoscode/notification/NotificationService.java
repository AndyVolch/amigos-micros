package com.amigoscode.notification;

import com.amigoscode.clients.notification.NotificationRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public record NotificationService(NotificationRepository repository) {
    public void sendNotification(NotificationRequest request) {

        Notification notification = Notification.builder()
                .toCustomerId(request.toCustomerId())
                .toCustomerEmail(request.toCustomerEmail())
                .sender("AmigosCode")
                .message(request.message())
                .sentAt(LocalDateTime.now())
                .build();

        repository.save(notification);
    }
}
