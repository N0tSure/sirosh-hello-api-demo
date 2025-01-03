package com.sirosh.spsec.key;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Optional;

@Component
class ApiKeyAuthenticationFilter implements Filter {

    private String apiKey;

    @Override
    public void doFilter(final ServletRequest rq, ServletResponse rs, FilterChain c)
            throws IOException, ServletException {

        final var r = (HttpServletRequest) rq;
        final var a = Optional.ofNullable(r.getHeader("X-HelloApiKey"));
        if (a.isPresent() && apiKey.equals(a.get())) {
            c.doFilter(rq, rs);
        } else {
            ((HttpServletResponse) rs).sendError(HttpServletResponse.SC_UNAUTHORIZED);
        }
    }

    @Autowired
    public void setApiKey(@Value("${service.api.key}") final String apiKey) {
        this.apiKey = apiKey;
    }
}
