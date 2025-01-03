package com.sirosh.spsec.key;

import com.sirosh.spsec.key.client.ApiClient;
import com.sirosh.spsec.key.client.HelloApi;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.web.client.HttpClientErrorException;

import java.net.URI;

import static org.assertj.core.api.Assertions.assertThatCode;

@SpringJUnitConfig
@TestPropertySource(properties = "service.test.base.uri=http://localhost:8080")
class HelloApiCTest {

    @Nested
    @TestPropertySource(properties = "service.api.key=foobarbaz")
    class GettingSuccessfulHelloTest {

        @Autowired
        private HelloApi api;

        @Test
        void shouldGetHelloWhenValidApiKey() {
            assertThatCode(() -> api.getHello())
                    .doesNotThrowAnyException();
        }
    }

    @Nested
    @TestPropertySource(properties = {"service.api.key=qux"})
    class GettingFailedHelloTest {

        @Autowired
        private HelloApi api;

        @Test
        void shouldFailHelloWhenInvalidApiKey() {
            assertThatCode(() -> api.getHello())
                    .isNotNull();
        }

        @Test
        void shouldThrowSpecificExcWhenInvalidApiKey() {
            assertThatCode(() -> api.getHello())
                    .isInstanceOf(HttpClientErrorException.Unauthorized.class);
        }
    }

    @TestConfiguration
    static class TestConfig {

        @Bean
        HelloApi helloApi(
                @Value("${service.api.key}") final String apiKey,
                @Value("${service.test.base.uri}") final URI baseURI
        ) {
            final var cli = new ApiClient();
            cli.setApiKey(apiKey);
            cli.setBasePath(baseURI.toString());
            return new HelloApi(cli);
        }
    }
}
