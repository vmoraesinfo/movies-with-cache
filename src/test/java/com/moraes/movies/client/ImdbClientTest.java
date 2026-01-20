package com.moraes.movies.client;

import com.moraes.movies.DTO.movie.MovieResponse;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.test.StepVerifier;

import static org.assertj.core.api.Assertions.assertThat;

class ImdbClientTest {

    private MockWebServer mockWebServer;
    private ImdbClient imdbClient;

    @BeforeEach
    void setUp() throws Exception {
        mockWebServer = new MockWebServer();
        mockWebServer.start();

        WebClient webClient = WebClient.builder()
                .baseUrl(mockWebServer.url("/").toString())
                .build();

        imdbClient = new ImdbClient(webClient);
    }

    @AfterEach
    void tearDown() throws Exception {
        mockWebServer.shutdown();
    }

    @Test
    void shouldReturnMovieResponseFromImdbApi() {
        String jsonResponse = """
            {
              "titles": [
                { "primaryTitle": "Batman" }
              ]
            }
            """;

        mockWebServer.enqueue(
                new MockResponse()
                        .setBody(jsonResponse)
                        .addHeader("Content-Type", "application/json")
        );

        StepVerifier.create(imdbClient.getAllTitles())
                .assertNext(response -> {
                    assertThat(response).isInstanceOf(MovieResponse.class);
                    assertThat(response.getTitles()).hasSize(1);
                    assertThat(response.getTitles().get(0).getPrimaryTitle())
                            .isEqualTo("Batman");
                })
                .verifyComplete();
    }
}
