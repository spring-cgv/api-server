package com.cgv.service;

import com.cgv.domain.dto.DiscountPolicyDto;

import javax.management.InstanceAlreadyExistsException;

public interface DiscountPolicyService {
    void saveDiscountPolicy(DiscountPolicyDto discountPolicyDto) throws InstanceAlreadyExistsException;

    void editDiscountPolicy(Long scheduleId, DiscountPolicyDto discountPolicyDto);

    DiscountPolicyDto findByScheduleId(Long scheduleId);
}
