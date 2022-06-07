package com.cgv.service.impl;

import com.cgv.domain.dto.DiscountPolicyDto;
import com.cgv.domain.entity.DiscountPolicy;
import com.cgv.domain.entity.Schedule;
import com.cgv.repository.DiscountPolicyRepository;
import com.cgv.repository.ScheduleRepository;
import com.cgv.service.DiscountPolicyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Service
public class DiscountPolicyServiceImpl implements DiscountPolicyService {

    private final DiscountPolicyRepository discountPolicyRepository;
    private final ScheduleRepository scheduleRepository;

    @Override
    public void saveDiscountPolicy(DiscountPolicyDto discountPolicyDto) {
        Schedule schedule = scheduleRepository.findById(discountPolicyDto.getScheduleId())
                .orElseThrow(NoSuchElementException::new);

        DiscountPolicy discountPolicy = DiscountPolicy.builder()
                .schedule(schedule)
                .type(discountPolicyDto.getType())
                .value(discountPolicyDto.getValue())
                .build();

        discountPolicyRepository.save(discountPolicy);
    }
}
