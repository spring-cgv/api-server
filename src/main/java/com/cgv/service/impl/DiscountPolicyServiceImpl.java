package com.cgv.service.impl;

import com.cgv.domain.dto.DiscountPolicyDto;
import com.cgv.domain.entity.DiscountPolicy;
import com.cgv.domain.entity.Schedule;
import com.cgv.repository.DiscountPolicyRepository;
import com.cgv.repository.ScheduleRepository;
import com.cgv.service.DiscountPolicyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.management.InstanceAlreadyExistsException;
import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Service
public class DiscountPolicyServiceImpl implements DiscountPolicyService {

    private final DiscountPolicyRepository discountPolicyRepository;
    private final ScheduleRepository scheduleRepository;

    @Override
    public void saveDiscountPolicy(DiscountPolicyDto discountPolicyDto) throws InstanceAlreadyExistsException {
        Schedule schedule = scheduleRepository.findById(discountPolicyDto.getScheduleId())
                .orElseThrow(NoSuchElementException::new);

        if (schedule.getDiscountPolicy() != null) {
            throw new InstanceAlreadyExistsException();
        }

        DiscountPolicy discountPolicy = DiscountPolicy.builder()
                .schedule(schedule)
                .type(discountPolicyDto.getType())
                .value(discountPolicyDto.getValue())
                .build();

        discountPolicyRepository.save(discountPolicy);
    }

    @Override
    public void editDiscountPolicy(Long discountPolicyId, DiscountPolicyDto discountPolicyDto) {
        DiscountPolicy discountPolicy = discountPolicyRepository.findById(discountPolicyId)
                .orElseThrow(NoSuchElementException::new);

        discountPolicy.setType(discountPolicyDto.getType());
        discountPolicy.setValue(discountPolicyDto.getValue());

        discountPolicyRepository.save(discountPolicy);
    }

    @Override
    public DiscountPolicyDto findByScheduleId(Long scheduleId) {
        return discountPolicyRepository
                .findByScheduleId(scheduleId)
                .map(DiscountPolicyDto::new)
                .orElse(null);
    }
}
