package com.cgv.service.impl;

import com.cgv.domain.Gender;
import com.cgv.domain.dto.MovieDto;
import com.cgv.domain.dto.TicketDistributionDto;
import com.cgv.domain.entity.User;
import com.cgv.repository.MovieRepository;
import com.cgv.repository.TicketRepository;
import com.cgv.service.MovieService;
import com.cgv.util.DateUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;
    private final TicketRepository ticketRepository;

    @Override
    public MovieDto findById(Long movieId) {
        return movieRepository.findById(movieId)
                .map(MovieDto::new)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public TicketDistributionDto getTicketDistribution(Long movieId) {
        if (!movieRepository.existsById(movieId))
            throw new EntityNotFoundException();

        List<User> users = ticketRepository.findTicketedUsersByMovieId(movieId);

        Map<Gender, Long> genderCount = users.stream()
                .collect(Collectors.groupingBy(
                        User::getGender,
                        Collectors.counting()));

        Map<Integer, Long> ageGroupCount = users.stream()
                .map(User::getBirth)
                .map(DateUtil::getAge)
                .map(age -> age / 10 * 10)
                .collect(Collectors.groupingBy(
                        Function.identity(),
                        Collectors.counting()));

        return new TicketDistributionDto(users.size(), genderCount, ageGroupCount);
    }
}
