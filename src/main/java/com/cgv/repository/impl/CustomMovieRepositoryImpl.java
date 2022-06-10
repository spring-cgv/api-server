package com.cgv.repository.impl;

import com.cgv.domain.dto.MovieDto;
import com.cgv.domain.entity.*;
import com.cgv.repository.CustomMovieRepository;
import com.cgv.util.QuerydslUtil;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.NumberExpression;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class CustomMovieRepositoryImpl implements CustomMovieRepository {

    private final JPAQueryFactory queryFactory;
    private final QMovie qMovie = QMovie.movie;
    private final QSchedule qSchedule = QSchedule.schedule;
    private final QTicket qTicket = QTicket.ticket;
    private final QReview qReview = QReview.review;
    private final NumberPath<Double> reviewStarAvgPath = Expressions.numberPath(Double.class, "reviewStarAvg");
    private final NumberPath<Double> ticketRatioPath = Expressions.numberPath(Double.class, "ticketRatio");

    @Override
    public List<MovieDto> findMovies(String titleKey, String actorKey, Pageable pageable) {
        return queryFactory.select(Projections.fields(MovieDto.class,
                        qMovie.title,
                        qMovie.synopsis,
                        qMovie.posterUrl,
                        qMovie.openingDate,
                        qMovie.runningTime,
                        qMovie.rating,
                        qMovie.director,
                        qMovie.actor,
                        qMovie.genre,
                        ExpressionUtils.as(getReviewStarAvg(), reviewStarAvgPath),
                        ExpressionUtils.as(getTicketRatio(), ticketRatioPath)))
                .from(qMovie)
                .where(hasTitleLike(titleKey),
                        hasActorLike(actorKey))
                .orderBy(QuerydslUtil.ordersFromPageable(pageable.getSort()))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
    }

    private BooleanExpression hasTitleLike(String key) {
        if (key == null)
            return null;
        return qMovie.title.contains(key);
    }

    private BooleanExpression hasActorLike(String key) {
        if (key == null)
            return null;
        return qMovie.actor.contains(key);
    }

    private JPQLQuery<Double> getReviewStarAvg() {
        return JPAExpressions.select(qReview.star.avg())
                .from(qReview)
                .where(qReview.movie.eq(qMovie));
    }

    private NumberExpression<Double> getTicketRatio() {
        return Expressions
                .asNumber(JPAExpressions.select(qTicket.count())
                        .from(qTicket)
                        .join(qTicket.schedule, qSchedule)
                        .where(qSchedule.movie.eq(qMovie)))
                .doubleValue()
                .divide(JPAExpressions.select(qTicket.count())
                        .from(qTicket));
    }
}
