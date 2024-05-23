package com.fitnesshub.subscription.entities;

import com.fitnesshub.subscription.SubscriptionStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.catalina.User;

import java.util.Date;

@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SubscriptionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private Date startDate;
    private Date endDate;

    @Enumerated(EnumType.STRING)
    private SubscriptionStatus status;

    @ManyToOne
    private SubscriptionPlan subscriptionPlan;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;
}
