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
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.HashMap;
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
        return movieRepository.findDtoById(movieId)
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

        Map<Integer, Long> ageGroupCountMap = users.stream()
                .map(User::getBirth)
                .map(DateUtil::getAge)
                .map(age -> age / 10 * 10)
                .collect(Collectors.groupingBy(
                        Function.identity(),
                        Collectors.counting()));

        for (int i = 10; i <= 50; i += 10) {
            ageGroupCountMap.putIfAbsent(i, 0L);
        }

        List<Map<String, Object>> ageGroupCountList = ageGroupCountMap.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByKey())
                .map(entry -> {
                    Map<String, Object> map = new HashMap();
                    map.put("ageGroup", entry.getKey() + "대");
                    map.put("count", entry.getValue());
                    return map;
                })
                .collect(Collectors.toList());

        return new TicketDistributionDto(users.size(), genderCount, ageGroupCountList);
    }

    @Override
    public List<MovieDto> findMoviesOnCondition(String titleKey, String actorKey, Pageable pageable) {
        return movieRepository.findMovies(titleKey, actorKey, pageable);
    }
}
