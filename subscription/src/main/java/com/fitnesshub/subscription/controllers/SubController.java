package com.fitnesshub.subscription.controllers;

import com.fitnesshub.subscription.entities.SubscriptionEntity;
import com.fitnesshub.subscription.entities.SubscriptionPlan;
import com.fitnesshub.subscription.service.SubService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sub")
public class SubController {

    private SubService subService;

    @PostMapping("/add")
    public ResponseEntity<SubscriptionEntity> addSubscription(@RequestParam int plan_id) {


        return null;
    }

}
