package com.cgv.web.controller;

import com.cgv.domain.dto.DiscountPolicyDto;
import com.cgv.domain.dto.ValidationGroup;
import com.cgv.service.DiscountPolicyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.management.InstanceAlreadyExistsException;
import java.util.NoSuchElementException;

@RequiredArgsConstructor
@RestController
@RequestMapping("/discount-policies")
public class DiscountPolicyController {

    private final DiscountPolicyService discountPolicyService;
    private final DiscountPolicyValidator discountPolicyValidator;

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.addValidators(discountPolicyValidator);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("")
    public ResponseEntity saveDiscountPolicy(@RequestBody @Validated(ValidationGroup.WithId.class) DiscountPolicyDto discountPolicyDto) {
        try {
            discountPolicyService.saveDiscountPolicy(discountPolicyDto);
            return new ResponseEntity(HttpStatus.CREATED);
        } catch (InstanceAlreadyExistsException e) {
            return new ResponseEntity(HttpStatus.CONFLICT);
        } catch (NoSuchElementException e) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PatchMapping("/{id}")
    public ResponseEntity editDiscountPolicy(@PathVariable("id") Long discountPolicyId,
                                             @RequestBody @Validated DiscountPolicyDto discountPolicyDto) {

        try {
            discountPolicyService.editDiscountPolicy(discountPolicyId, discountPolicyDto);
            return new ResponseEntity(HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }
}
