package com.cgv.web.controller;

import com.cgv.domain.DiscountType;
import com.cgv.domain.dto.DiscountPolicyDto;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class DiscountPolicyValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return DiscountPolicyDto.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        DiscountPolicyDto discountPolicyDto = (DiscountPolicyDto) target;
        DiscountType discountPolicyType = discountPolicyDto.getType();
        Integer discountValue = discountPolicyDto.getValue();

        if (discountPolicyType == null || discountValue == null)
            return;

        if (discountPolicyType == DiscountType.PERCENT) {
            if (discountValue <= 0 || discountValue >= 100) {
                errors.rejectValue("value", null, "정률할인 범위는 0% 초과 100% 미만입니다.");
            }
        } else {
            if (discountValue <= 0 || discountValue > 5000) {
                errors.rejectValue("value", null, "정액할인 범위는 0원 초과 5000원 이하입니다.");
            }
        }
    }
}
