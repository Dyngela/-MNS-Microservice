package com.ne.notification;

import com.ne.clients.notification.NotificationRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class NotificationService {

    private final NotificationRepository notificationRepository;

    public void send(NotificationRequest request) {
        notificationRepository.save(Notification.builder()
                .toCustomerId(request.toCustomerId())
                .toCustomerEmail(request.toCustomerName())
                .sender("Guth Aymeric")
                .message("Java est trop bien")
                .sentAt(LocalDateTime.now())
                .build());
    }
}
