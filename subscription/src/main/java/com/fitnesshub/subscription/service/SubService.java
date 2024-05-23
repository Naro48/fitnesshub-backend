package com.fitnesshub.subscription.service;

import com.fitnesshub.subscription.SubscriptionStatus;
import com.fitnesshub.subscription.UserType;
import com.fitnesshub.subscription.entities.SubscriptionEntity;
import com.fitnesshub.subscription.entities.SubscriptionPlan;
import com.fitnesshub.subscription.entities.UserEntity;

public class SubService implements SubInt{

    @Override
    public String subscribe(UserEntity user, SubscriptionPlan plan) {
        SubscriptionEntity subscription = new SubscriptionEntity();

        if (user.getUserType().equals(UserType.SUBSCRIBED_USER)){
            return "The user is already subscribed";
        }

        subscription.setUser(user);
        subscription.setSubscriptionPlan(plan);
        user.setUserType(UserType.SUBSCRIBED_USER);

        subscription.setStatus(SubscriptionStatus.ACTIVE);


        return "The user is subscribed to the subscription plan " ;
    }

    @Override
    public void unsubscribe(SubscriptionEntity subscription) {
        subscription.setStatus(SubscriptionStatus.SUSPENDED);

    }

    @Override
    public void changeSubscription(SubscriptionEntity subscription, SubscriptionPlan plan) {
        subscription.setSubscriptionPlan(plan);
    }

}
