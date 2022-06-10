package com.cgv.repository;

import com.cgv.domain.entity.DiscountPolicy;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DiscountPolicyRepository extends JpaRepository<DiscountPolicy, Long> {
    Optional<DiscountPolicy> findByScheduleId(Long scheduleId);
}
