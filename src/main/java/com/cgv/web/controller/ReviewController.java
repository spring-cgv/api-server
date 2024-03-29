package com.cgv.web.controller;

import com.cgv.domain.CustomUser;
import com.cgv.domain.dto.ReviewDto;
import com.cgv.domain.dto.ValidationGroup;
import com.cgv.service.LikeService;
import com.cgv.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RequiredArgsConstructor
@RestController
@RequestMapping("/reviews")
public class ReviewController {

    private final ReviewService reviewService;
    private final LikeService likeService;

    @PostMapping("")
    public ResponseEntity saveReview(@RequestBody @Validated(ValidationGroup.WithId.class) ReviewDto reviewDto,
                                     @AuthenticationPrincipal CustomUser customUser) {

        try {
            reviewService.saveReview(reviewDto, customUser.getUsername());
            return new ResponseEntity(HttpStatus.CREATED);
        } catch (NoSuchElementException e) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity editReview(@PathVariable("id") Long reviewId,
                                     @RequestBody @Validated ReviewDto reviewDto,
                                     @AuthenticationPrincipal CustomUser customUser) {

        try {
            reviewService.editReview(reviewId, reviewDto, customUser.getUsername());
            return new ResponseEntity(HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        } catch (AccessDeniedException e) {
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity removeReview(@PathVariable("id") Long reviewId,
                                       @AuthenticationPrincipal CustomUser customUser) {

        try {
            reviewService.removeReview(reviewId, customUser.getUsername());
            return new ResponseEntity(HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        } catch (AccessDeniedException e) {
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping("/{id}/likes")
    public ResponseEntity likeReview(@PathVariable("id") Long reviewId,
                                     @AuthenticationPrincipal CustomUser customUser) {

        try {
            likeService.insertLike(reviewId, customUser.getUsername());
            return new ResponseEntity(HttpStatus.CREATED);
        } catch (NoSuchElementException e) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        } catch (DataIntegrityViolationException e) {
            return new ResponseEntity(HttpStatus.CONFLICT);
        }
    }

    @DeleteMapping("/{id}/likes")
    public ResponseEntity unlikeReview(@PathVariable("id") Long reviewId,
                                       @AuthenticationPrincipal CustomUser customUser) {

        try {
            likeService.deleteLike(reviewId, customUser.getUsername());
            return new ResponseEntity(HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }
}
