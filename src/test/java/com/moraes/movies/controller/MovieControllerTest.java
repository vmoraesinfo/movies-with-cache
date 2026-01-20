package com.moraes.movies.controller;

import com.moraes.movies.DTO.movie.Movie;
import com.moraes.movies.service.MovieService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.List;

import static org.mockito.Mockito.when;

@WebFluxTest(MovieController.class)
class MovieControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private MovieService service;

    @Test
    void shouldReturnAllMoviesWhenNoTitleParam() {
        Movie movie = new Movie();
        movie.setPrimaryTitle("Batman");

        when(service.getMovies())
                .thenReturn(List.of(movie));

        webTestClient.get()
                .uri("/movies")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Movie.class)
                .hasSize(1);
    }

    @Test
    void shouldReturnMoviesByTitleWhenTitleParamIsProvided() {
        Movie movie = new Movie();
        movie.setPrimaryTitle("Batman");

        when(service.getMovieByTitle("Batman"))
                .thenReturn(List.of(movie));

        webTestClient.get()
                .uri(uriBuilder ->
                        uriBuilder.path("/movies")
                                .queryParam("title", "Batman")
                                .build()
                )
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Movie.class)
                .hasSize(1);
    }

    @Test
    void shouldReturnTitlesAsJsonArray() {
        when(service.getByMovieName())
                .thenReturn(List.of("Batman", "Superman"));

        webTestClient.get()
                .uri("/movies/titlesName")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .json("[\"Batman\",\"Superman\"]");
    }

    @Test
    void shouldReturnPostersAsJsonArray() {
        when(service.getMoviesPoster())
                .thenReturn(List.of("poster1", "poster2"));

        webTestClient.get()
                .uri("/movies/poster")
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType("application/json")
                .expectBody()
                .json("[\"poster1\",\"poster2\"]");
    }
}
