package com.cgv.repository;

import com.cgv.domain.entity.Like;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like, Long> {
    Optional<Like> findByReviewIdAndUserUsername(Long reviewId, String username);
}
