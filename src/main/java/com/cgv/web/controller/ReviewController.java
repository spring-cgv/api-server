package com.cgv.web.controller;

import com.cgv.domain.CustomUser;
import com.cgv.domain.dto.ReviewDto;
import com.cgv.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RequiredArgsConstructor
@RestController
@RequestMapping("/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping("")
    public List<ReviewDto> getReviewsByPage(@PageableDefault(size = 6) Pageable pageable,
                                            @AuthenticationPrincipal CustomUser customUser) {

        return reviewService.getReviewsByPage(pageable, customUser);
    }

    @PostMapping("")
    public ResponseEntity saveReview(@RequestBody @Validated ReviewDto reviewDto,
                                     @AuthenticationPrincipal CustomUser customUser) {

        try {
            reviewService.saveReview(reviewDto, customUser.getUsername());
            return new ResponseEntity(HttpStatus.CREATED);
        } catch (NoSuchElementException e) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }
}
