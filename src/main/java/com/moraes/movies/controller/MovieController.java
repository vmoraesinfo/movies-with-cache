package com.moraes.movies.controller;

import com.moraes.movies.DTO.movie.Movie;
import com.moraes.movies.service.MovieService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {

    private final MovieService service;

    public MovieController(MovieService service) {
        this.service = service;
    }

    @GetMapping
    public Mono<List<Movie>> getMovies(@RequestParam(required = false) String title) {

        if (title == null) {
            return Mono.fromCallable(service::getMovies)
                    .subscribeOn(Schedulers.boundedElastic());
        }

        return Mono.fromCallable(() -> service.getMovieByTitle(title))
                .subscribeOn(Schedulers.boundedElastic());
    }

    @GetMapping("/titlesName")
    public Mono<List<String>> getByMovieName() {
        return Mono.fromCallable(service::getByMovieName)
                .subscribeOn(Schedulers.boundedElastic());
    }

    @GetMapping("/poster")
    public Mono<List<String>> getAllMoviePoster() {
        return Mono.fromCallable(service::getMoviesPoster)
                .subscribeOn(Schedulers.boundedElastic());
    }
}
