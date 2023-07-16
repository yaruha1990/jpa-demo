package com.example.demo.security.filter;

import com.example.demo.security.config.SecurityProperties;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
@Component
@RequiredArgsConstructor
public class ApiKeySimpleFilter extends OncePerRequestFilter {

    private static final String HEADER_X_API_KEY = "x-api-key";
    private static final String VALID_KEY = "valid-key";
    private final SecurityProperties securityProperties;

    @Override
    protected void doFilterInternal(
            final HttpServletRequest request,
            final HttpServletResponse response,
            final FilterChain filterChain
    ) throws ServletException, IOException {

        final String apiKey = request.getHeader(HEADER_X_API_KEY);

        if (apiKey == null || apiKey.isBlank()) {
            throw new BadCredentialsException("API KEY is not provided");
        }

        if (VALID_KEY.equals(apiKey)) {
            log.info("Valid API KEY provided");
            filterChain.doFilter(request, response);
        } else {
            log.debug("Invalid API KEY provided ");
            throw new BadCredentialsException("Invalid API KEY");
        }
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        return securityProperties.getWhiteList()
                .stream()
                .anyMatch(requestURI::startsWith);
    }
}
