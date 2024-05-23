package com.fitnesshub.subscription.repositories;

import com.fitnesshub.subscription.entities.SubscriptionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubRepo extends JpaRepository<SubscriptionEntity, Integer> {


}
