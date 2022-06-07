package com.cgv.service;

import com.cgv.domain.dto.DiscountPolicyDto;

public interface DiscountPolicyService {
    void saveDiscountPolicy(DiscountPolicyDto discountPolicyDto);

    void editDiscountPolicy(Long discountPolicyId, DiscountPolicyDto discountPolicyDto);
}
