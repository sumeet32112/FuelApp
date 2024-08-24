package com.noxfilers.fuelApp.entities;

import javax.persistence.*;
import lombok.*;
import java.util.Date;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "notification_template")
public class NotificationTemplate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "notification_code", length = 255, nullable = false, unique = true)
    private String notificationCode;

    @Column(name = "notification_subject", length = 50)
    private String notificationSubject;

    @Column(name = "notification_text", length = 255, nullable = false)
    private String notificationText;

    @Column(name = "notification_type", length = 10, nullable = false)
    private String notificationType;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "common_details", referencedColumnName = "id")
    private CommonDetails commonDetails;

}
