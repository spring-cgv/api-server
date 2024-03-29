package com.cgv.domain.dto;

import com.cgv.domain.entity.Review;
import com.cgv.domain.entity.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class ReviewDto {
    private Long id;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotNull(groups = ValidationGroup.WithId.class)
    private Long movieId;

    private Boolean isWriter;

    @NotNull
    private Integer star;

    @NotNull
    private String comment;

    private LocalDateTime createdDateTime;

    private Integer likesCount;

    private Boolean isUserLiked;

    public ReviewDto(Review review, User principalUser) {
        this.id = review.getId();
        this.isWriter = review.getUser().equals(principalUser);
        this.star = review.getStar();
        this.comment = review.getComment();
        this.createdDateTime = review.getCreatedDateTime();
        this.likesCount = review.getLikes().size();
        this.isUserLiked = review.getLikes()
                .stream()
                .anyMatch(like -> like.getUser().equals(principalUser));
    }
}
