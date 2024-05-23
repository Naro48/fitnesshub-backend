package com.fitnesshub.subscription.repositories;

import com.fitnesshub.subscription.entities.SubscriptionPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubPlanRepo extends JpaRepository<SubscriptionPlan,Integer> {


}
