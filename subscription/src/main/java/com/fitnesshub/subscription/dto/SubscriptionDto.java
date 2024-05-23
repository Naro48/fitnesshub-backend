package com.fitnesshub.subscription.dto;

import com.fitnesshub.subscription.SubscriptionStatus;
import com.fitnesshub.subscription.entities.SubscriptionPlan;
import com.fitnesshub.subscription.entities.UserEntity;

import java.util.Date;

public class SubscriptionDto {

    private Date startDate;

    private Date endDate;

    private String title;

    private String description;

    private SubscriptionStatus status;

    private SubscriptionPlan plan ;

    private UserEntity user;
}
