package com.cgv.domain.dto;

import com.cgv.domain.DiscountType;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
public class DiscountPolicyDto {
    @NotNull
    private Long scheduleId;

    @NotNull(groups = ValidationGroup.WithoutSchedule.class)
    private DiscountType type;

    @NotNull(groups = ValidationGroup.WithoutSchedule.class)
    private Integer value;
}