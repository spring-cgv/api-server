package com.cgv.service;

import com.cgv.domain.dto.MovieDto;
import com.cgv.domain.dto.TicketDistributionDto;

public interface MovieService {
    MovieDto findById(Long movieId);

    TicketDistributionDto getTicketDistribution(Long movieId);
}
