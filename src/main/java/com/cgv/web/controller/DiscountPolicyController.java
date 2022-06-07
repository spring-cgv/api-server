package com.cgv.web.controller;

import com.cgv.domain.dto.DiscountPolicyDto;
import com.cgv.service.DiscountPolicyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.NoSuchElementException;

@RequiredArgsConstructor
@RestController
@RequestMapping("/discount-policies")
public class DiscountPolicyController {

    private final DiscountPolicyService discountPolicyService;

    @PostMapping("")
    public ResponseEntity saveDiscountPolicy(@RequestBody @Valid DiscountPolicyDto discountPolicyDto) {
        try {
            discountPolicyService.saveDiscountPolicy(discountPolicyDto);
            return new ResponseEntity(HttpStatus.CREATED);
        } catch (NoSuchElementException e) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }
}
