package com.cgv.web.controller;

import com.cgv.domain.dto.DiscountPolicyDto;
import com.cgv.service.DiscountPolicyService;
import com.cgv.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/schedules")
public class ScheduleController {

    private final ScheduleService scheduleService;
    private final DiscountPolicyService discountPolicyService;

    @GetMapping("")
    public List<Map<String, Object>> getSchedules(
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate screenDate) {

        if (screenDate == null) {
            screenDate = LocalDate.now().plusDays(1);
        }

        return scheduleService.findSchedulesOnDate(screenDate);
    }

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
