package com.ne.notification;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Builder
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Notification {
    @Id
    @SequenceGenerator(name = "notification_sequence", sequenceName = "notification_sequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "notification_sequence")
    private Integer id;
    private Integer toCustomerId;
    private String toCustomerEmail;
    private String sender;
    private String message;
    private LocalDateTime sentAt;

}
