package com.sirosh.spsec.key;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
class Config {

    @Bean
    SecurityFilterChain securityFilterChain(final HttpSecurity hs, final ApiKeyAuthenticationFilter f)
            throws Exception {

        hs.addFilterAt(f, BasicAuthenticationFilter.class);
        hs.authorizeHttpRequests(c -> c.anyRequest().permitAll());

        return hs.build();
    }
}
