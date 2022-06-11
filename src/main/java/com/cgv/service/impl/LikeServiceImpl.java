package com.cgv.service.impl;

import com.cgv.domain.entity.Like;
import com.cgv.repository.LikeRepository;
import com.cgv.repository.ReviewRepository;
import com.cgv.repository.UserRepository;
import com.cgv.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class LikeServiceImpl implements LikeService {

    private final LikeRepository likeRepository;
    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;

    @Override
    public void insertLike(Long reviewId, String username) {
        Like like = Like.builder()
                .review(reviewRepository.findById(reviewId).get())
                .user(userRepository.findByUsername(username).get())
                .build();
        likeRepository.save(like);
    }

    @Override
    public void deleteLike(Long reviewId, String username) {
        Like like = likeRepository.findByReviewIdAndUserUsername(reviewId, username).get();
        likeRepository.delete(like);
    }
}
