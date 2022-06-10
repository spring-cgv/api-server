package com.cgv.service;

import com.cgv.domain.dto.MovieDto;
import com.cgv.domain.dto.TicketDistributionDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MovieService {
    MovieDto findById(Long movieId);

    TicketDistributionDto getTicketDistribution(Long movieId);

    List<MovieDto> findMoviesOnCondition(String titleKey, String actorKey, Pageable pageable);
}
