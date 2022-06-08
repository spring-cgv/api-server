package com.cgv.domain.dto;

import com.cgv.domain.DiscountType;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
public class DiscountPolicyDto {
    @NotNull(groups = ValidationGroup.WithId.class)
    private Long scheduleId;

    @NotNull
    private DiscountType type;

    @NotNull
    private Integer value;
}