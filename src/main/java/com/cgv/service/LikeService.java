package com.cgv.service;

public interface LikeService {
    void insertLike(Long reviewId, String username);

    void deleteLike(Long reviewId, String username);
}
