package com.fitnesshub.subscription.controllers;

import com.fitnesshub.subscription.UserType;
import com.fitnesshub.subscription.entities.SubscriptionEntity;
import com.fitnesshub.subscription.entities.SubscriptionPlan;
import com.fitnesshub.subscription.entities.UserEntity;
import com.fitnesshub.subscription.repositories.UserRepo;
import com.fitnesshub.subscription.service.SubService;
import com.google.common.net.HttpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/subscriptions")
public class SubController {

    @Autowired
    private SubService subService;

    @Autowired
    private UserRepo userRepo;

    @PostMapping("/subscribe")
    public String subscribe(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader, @RequestBody SubscriptionPlan plan) {
        UserEntity user = subService.getUserFromToken(subService.getToken(authorizationHeader));

        if (user == null) {
            return "Utilisateur Introuvable";
        } else if (userRepo.findById(user.getId()) != null) {
            subService.subscribe(user, plan);
        }

        user.setUserType(UserType.NON_SUBSCRIBED_USER);
        subService.subscribe(user, plan);

        return "Subscribed";
    }

    @PutMapping("/unsubscribe/{id}")
    public void unsubscribe(@PathVariable Long id) {
        SubscriptionEntity subscription = new SubscriptionEntity();
        subscription.setId(id);
        subService.unsubscribe(subscription);
    }

    @PutMapping("/change-subscription/{id}")
    public void changeSubscription(@PathVariable Long id, @RequestBody SubscriptionPlan plan) {
        SubscriptionEntity subscription = new SubscriptionEntity();
        subscription.setId(id);
        subService.changeSubscription(subscription, plan);
    }
}