package com.fitnesshub.subscription.service;

import com.fitnesshub.subscription.SubscriptionStatus;
import com.fitnesshub.subscription.UserType;
import com.fitnesshub.subscription.entities.SubscriptionEntity;
import com.fitnesshub.subscription.entities.SubscriptionPlan;
import com.fitnesshub.subscription.entities.UserEntity;
import com.fitnesshub.subscription.repositories.SubRepo;
import com.fitnesshub.subscription.repositories.SubRepo;
import org.apache.catalina.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class SubService implements SubInt{

    private final SubRepo subscriptionRepository;

    private RestTemplate restTemplate;

    private static final String authServiceUrl = "http://localhost:9898/auth"; ;

    public SubService(SubRepo subscriptionRepository) {
        this.subscriptionRepository = subscriptionRepository;
    }

    @Override
    public String subscribe(UserEntity user, SubscriptionPlan plan) {
        if (user == null || plan == null) {
            return "Invalid user or subscription plan";
        }

        if (user.getUserType().equals(UserType.SUBSCRIBED_USER)) {
            return "The user is already subscribed";
        }

        SubscriptionEntity subscription = new SubscriptionEntity();
        subscription.setUser(user);
        subscription.setSubscriptionPlan(plan);
        subscription.setStatus(SubscriptionStatus.ACTIVE);
        user.setUserType(UserType.SUBSCRIBED_USER);

        subscriptionRepository.save(subscription);
        // Assuming there is a method to update user in the repository

        return "The user is successfully subscribed to the subscription plan ";
    }

    @Override
    public void unsubscribe(SubscriptionEntity subscription) {
        if (subscription == null) {
            throw new IllegalArgumentException("Subscription cannot be null");
        }

        subscription.setStatus(SubscriptionStatus.SUSPENDED);
        subscriptionRepository.save(subscription);
    }

    @Override
    public void changeSubscription(SubscriptionEntity subscription, SubscriptionPlan plan) {
        if (subscription == null || plan == null) {
            throw new IllegalArgumentException("Subscription or plan cannot be null");
        }

        subscription.setSubscriptionPlan(plan);
        subscriptionRepository.save(subscription);
    }

    @Override
    public String getToken(String authorizationHeader) {
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String token = authorizationHeader.substring(7); // Supprimer "Bearer " pour obtenir le token
            return token;
        } else {
            return "Authorization header is missing or invalid";
        }
    }

    @Override
    public UserEntity getUserFromToken(String token) {
        ResponseEntity<UserEntity> response = restTemplate.getForEntity(authServiceUrl + token, UserEntity.class);
        if (response.getStatusCode() != HttpStatus.OK) {
            throw new RuntimeException("Failed to get user credentials");
        }
        UserEntity user = new UserEntity();

        user.setId(response.getBody().getId());
        user.setUsername(response.getBody().getUsername());
        user.setEmail(response.getBody().getEmail());



        return user;
    }



}
