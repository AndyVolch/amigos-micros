package com.amigoscode.notification;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter @Setter @Builder @ToString
@AllArgsConstructor @NoArgsConstructor
@Entity
public class Notification {

    @Id
    @SequenceGenerator(name = "notification_generator",
            sequenceName = "notification_generator")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "notification_generator")
    private Integer notificationId;
    private Integer toCustomerId;
    private String toCustomerEmail;
    private String sender;
    private String message;
    private LocalDateTime sentAt;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Notification that = (Notification) o;
        if (notificationId == null) return false;
        return notificationId.equals(that.notificationId);
    }

    @Override
    public int hashCode() {
        return 0;
    }
}
