package com.cgv.web.controller;

import com.cgv.domain.CustomUser;
import com.cgv.domain.dto.ReviewDto;
import com.cgv.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
}
