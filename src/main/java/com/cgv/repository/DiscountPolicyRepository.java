package com.cgv.repository;

import com.cgv.domain.entity.DiscountPolicy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiscountPolicyRepository extends JpaRepository<DiscountPolicy, Long> {
}
