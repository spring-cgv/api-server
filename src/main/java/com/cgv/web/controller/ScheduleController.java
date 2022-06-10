package com.cgv.web.controller;

import com.cgv.domain.dto.DiscountPolicyDto;
import com.cgv.service.DiscountPolicyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.NoSuchElementException;

@RequiredArgsConstructor
@RestController
@RequestMapping("/schedules")
public class ScheduleController {

    private final DiscountPolicyService discountPolicyService;

    @GetMapping("/{id}/discount-policies")
    public ResponseEntity getDiscountPolicyFromSchedule(@PathVariable("id") Long scheduleId) {
        try {
            DiscountPolicyDto discountPolicyDto = discountPolicyService.findByScheduleId(scheduleId);
            return new ResponseEntity(discountPolicyDto, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }
}
