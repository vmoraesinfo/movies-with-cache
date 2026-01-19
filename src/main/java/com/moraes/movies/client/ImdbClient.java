package com.moraes.movies.client;

import com.moraes.movies.DTO.movie.MovieResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class ImdbClient {

    private static final Logger log = LoggerFactory.getLogger(ImdbClient.class);

    private final WebClient webClient;

    public ImdbClient(@Qualifier("imdbWebClient") WebClient webClient) {
        this.webClient = webClient;
    }

    public Mono<MovieResponse> getAllTitles() {

        log.info("🌐 CHAMANDO API IMDb (/titles)");

        return webClient.get()
                .uri("/titles")
                .retrieve()
                .bodyToMono(MovieResponse.class);
    }
}
