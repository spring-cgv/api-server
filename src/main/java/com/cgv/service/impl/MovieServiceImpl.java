package com.cgv.service.impl;

import com.cgv.domain.dto.MovieDto;
import com.cgv.repository.MovieRepository;
import com.cgv.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@RequiredArgsConstructor
@Service
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;

    @Override
    public MovieDto findById(Long movieId) {
        return movieRepository.findById(movieId)
                .map(MovieDto::new)
                .orElseThrow(EntityNotFoundException::new);
    }
}
