package com.cgv.repository;

import com.cgv.domain.dto.MovieDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CustomMovieRepository {
    List<MovieDto> findMovies(String titleKey, String actorKey, Pageable pageable);
}
