package com.cgv.domain.dto;

import com.cgv.domain.DiscountType;
import com.cgv.domain.entity.DiscountPolicy;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
public class DiscountPolicyDto {
    private Long id;

    @NotNull(groups = ValidationGroup.WithId.class)
    private Long scheduleId;

    @NotNull
    private DiscountType type;

    @NotNull
    private Integer value;

    public DiscountPolicyDto(DiscountPolicy discountPolicy) {
        this.id = discountPolicy.getId();
        this.scheduleId = discountPolicy.getSchedule().getId();
        this.type = discountPolicy.getType();
        this.value = discountPolicy.getValue();
    }
}