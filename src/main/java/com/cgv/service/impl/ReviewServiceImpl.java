package com.cgv.service.impl;

import com.cgv.domain.CustomUser;
import com.cgv.domain.dto.ReviewDto;
import com.cgv.domain.entity.Review;
import com.cgv.domain.entity.User;
import com.cgv.repository.MovieRepository;
import com.cgv.repository.ReviewRepository;
import com.cgv.repository.UserRepository;
import com.cgv.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final MovieRepository movieRepository;

    @Override
    public List<ReviewDto> getReviewsByPage(Pageable pageable, CustomUser principalCustomUser) {
        User principalUser = principalCustomUser == null ?
                null : userRepository.findByUsername(principalCustomUser.getUsername()).get();

        return reviewRepository.findAll(pageable)
                .stream()
                .map(review -> new ReviewDto(review, principalUser))
                .collect(Collectors.toList());
    }

    @Override
    public void saveReview(ReviewDto reviewDto, String username) {
        Review review = Review.builder()
                .movie(movieRepository.findById(reviewDto.getMovieId()).get())
                .user(userRepository.findByUsername(username).get())
                .star(reviewDto.getStar())
                .comment(reviewDto.getComment())
                .build();
        reviewRepository.save(review);
    }

    @Override
    public void removeReview(Long reviewId, String username) {
        Review review = reviewRepository.findById(reviewId).get();

        if (!review.getUser().getUsername().equals(username))
            throw new AccessDeniedException("Access to this review is denied.");

        reviewRepository.delete(review);
    }
}
