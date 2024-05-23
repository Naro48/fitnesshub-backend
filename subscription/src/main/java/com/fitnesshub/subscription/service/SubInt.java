package com.fitnesshub.subscription.service;

import com.fitnesshub.subscription.entities.SubscriptionEntity;
import com.fitnesshub.subscription.entities.SubscriptionPlan;
import com.fitnesshub.subscription.entities.UserEntity;

public interface SubInt {

    public String subscribe(UserEntity user , SubscriptionPlan plan);

    public void unsubscribe(SubscriptionEntity subscription);

    public void changeSubscription(SubscriptionEntity subscription , SubscriptionPlan plan);

    public String getToken(String authorizationHeader);

    public UserEntity getUserFromToken(String token);
}
