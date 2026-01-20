package com.moraes.movies.service;

import com.moraes.movies.DTO.movie.Image;
import com.moraes.movies.DTO.movie.Movie;
import com.moraes.movies.DTO.movie.MovieResponse;
import com.moraes.movies.client.ImdbClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MovieServiceTest {

    private ImdbClient client;
    private MovieService service;

    @BeforeEach
    void setUp() {
        client = mock(ImdbClient.class);
        service = new MovieService(client);
    }

    @Test
    void shouldReturnMovieTitles() {
        Movie movie1 = mock(Movie.class);
        Movie movie2 = mock(Movie.class);

        when(movie1.getPrimaryTitle()).thenReturn("Batman");
        when(movie2.getPrimaryTitle()).thenReturn("Superman");

        mockClientResponse(List.of(movie1, movie2));

        List<String> result = service.getByMovieName();

        assertEquals(List.of("Batman", "Superman"), result);
        verify(client, times(1)).getAllTitles();
    }

    @Test
    void shouldReturnOnlyValidPosterUrls() {
        Image image1 = mock(Image.class);
        when(image1.getUrl()).thenReturn("http://poster1.jpg");

        Image image2 = mock(Image.class);
        when(image2.getUrl()).thenReturn(null);

        Movie movie1 = mock(Movie.class);
        Movie movie2 = mock(Movie.class);
        Movie movie3 = mock(Movie.class);

        when(movie1.getPrimaryImage()).thenReturn(image1);
        when(movie2.getPrimaryImage()).thenReturn(image2);
        when(movie3.getPrimaryImage()).thenReturn(null);

        mockClientResponse(List.of(movie1, movie2, movie3));

        List<String> result = service.getMoviesPoster();

        assertEquals(1, result.size());
        assertEquals("http://poster1.jpg", result.get(0));
    }

    @Test
    void shouldReturnEmptyListWhenNoMovies() {
        mockClientResponse(List.of());

        List<String> titles = service.getByMovieName();
        List<String> posters = service.getMoviesPoster();

        assertTrue(titles.isEmpty());
        assertTrue(posters.isEmpty());
    }

    private void mockClientResponse(List<Movie> movies) {
        MovieResponse response = mock(MovieResponse.class);
        when(response.getTitles()).thenReturn(movies);
        when(client.getAllTitles()).thenReturn(Mono.just(response));
    }
}
