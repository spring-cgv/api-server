package com.cgv.domain.dto;

import com.cgv.domain.entity.Review;
import com.cgv.domain.entity.User;
import lombok.Getter;

@Getter
public class ReviewDto {
    private Long id;
    private Boolean isWriter;
    private Integer star;
    private String comment;

    public ReviewDto(Review review, User principalUser) {
        this.id = review.getId();
        this.isWriter = review.getUser().equals(principalUser);
        this.star = review.getStar();
        this.comment = review.getComment();
    }
}
