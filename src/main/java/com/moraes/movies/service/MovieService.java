package com.moraes.movies.service;

import com.moraes.movies.DTO.movie.Image;
import com.moraes.movies.DTO.movie.Movie;
import com.moraes.movies.DTO.movie.MovieResponse;
import com.moraes.movies.client.ImdbClient;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {

    private final ImdbClient client;

    public MovieService(ImdbClient client) {
        this.client = client;
    }

    @Cacheable(value = "movies")
    public List<Movie> getMovies() {
        System.out.println("🔥 CHAMANDO API IMDb");

        return client.getAllTitles()
                .map(MovieResponse::getTitles)
                .block();
    }

    public List<Movie> getMovieByTitle(String title) {
        return getMovies().stream()
                .filter(movie ->
                        movie.getPrimaryTitle() != null &&
                                movie.getPrimaryTitle().equalsIgnoreCase(title)
                )
                .toList();
    }

    public List<String> getByMovieName() {
        return getMovies().stream()
                .map(Movie::getPrimaryTitle)
                .toList();
    }

    public List<String> getMoviesPoster() {
        return getMovies().stream()
                .map(Movie::getPrimaryImage)
                .filter(image -> image != null && image.getUrl() != null)
                .map(Image::getUrl)
                .toList();
    }
}
