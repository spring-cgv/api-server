package com.cgv.service;

import com.cgv.domain.dto.MovieDto;

public interface MovieService {
    MovieDto findById(Long movieId);
}
