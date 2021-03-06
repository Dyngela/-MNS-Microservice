package com.ne.clients.notification;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(value = "notification")
public interface NotificationClient {
    @PostMapping("api/notification")
    void sendNotification(NotificationRequest request);
}
