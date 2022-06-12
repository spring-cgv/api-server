package com.cgv.service;

import com.cgv.domain.CustomUser;
import com.cgv.domain.dto.ReviewDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ReviewService {
    List<ReviewDto> getReviewsOnMovieByPage(Long movieId, Pageable pageable, CustomUser principalCustomUser);

    void saveReview(ReviewDto reviewDto, String username);

    void removeReview(Long reviewId, String username);

    void editReview(Long reviewId, ReviewDto reviewDto, String username);
}
