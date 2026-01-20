package com.moraes.movies.configuration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cache.CacheManager;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = RedisConfig.class)
class RedisConfigTest {

    @MockBean
    private RedisConnectionFactory redisConnectionFactory;

    @Autowired
    private CacheManager cacheManager;


    @Test
    void shouldCreateRedisCacheManagerBean() {
        assertThat(cacheManager)
                .isNotNull()
                .isInstanceOf(RedisCacheManager.class);
    }
}
