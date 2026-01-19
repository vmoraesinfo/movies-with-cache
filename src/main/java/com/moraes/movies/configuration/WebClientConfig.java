package com.moraes.movies.configuration;

import com.moraes.movies.propertis.ApisProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Bean
    public WebClient imdbWebClient(ApisProperties props) {
        return WebClient.builder()
                .baseUrl(props.getImdb())
                .build();
    }

    @Bean
    public WebClient nominatimWebClient(ApisProperties props) {
        return WebClient.builder()
                .baseUrl(props.getNominatim())
                .defaultHeader("Accept", "application/json")
                .build();
    }
}

