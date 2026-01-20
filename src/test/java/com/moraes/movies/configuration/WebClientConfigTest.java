package com.moraes.movies.configuration;

import com.moraes.movies.propertis.ApisProperties;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.web.reactive.function.client.WebClient;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = WebClientConfig.class)
class WebClientConfigTest {

    @Autowired
    private ApplicationContext context;

    @MockBean
    private ApisProperties apisProperties;

    @Test
    void shouldCreateImdbWebClientWithBaseUrl() {
        when(apisProperties.getImdb())
                .thenReturn("https://imdb-api.test");

        WebClient client = context.getBean("imdbWebClient", WebClient.class);

        assertThat(client).isNotNull();
    }

    @Test
    void shouldCreateNominatimWebClientWithBaseUrlAndHeader() {
        when(apisProperties.getNominatim())
                .thenReturn("https://nominatim-api.test");

        WebClient client = context.getBean("nominatimWebClient", WebClient.class);

        assertThat(client).isNotNull();
    }
}
