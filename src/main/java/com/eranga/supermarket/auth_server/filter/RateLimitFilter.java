package com.eranga.supermarket.auth_server.filter;

import com.eranga.supermarket.auth_server.service.JwtService;
import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Refill;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.Duration;
import java.util.concurrent.ConcurrentHashMap;

@Component
@RequiredArgsConstructor
public class RateLimitFilter extends OncePerRequestFilter {

    private final ConcurrentHashMap<String, Bucket> cache = new ConcurrentHashMap<>();
    private final JwtService jwtService;

    private Bucket createNewBucket() {
        Bandwidth limit = Bandwidth.classic(5, Refill.intervally(5, Duration.ofMinutes(5))); // 5 requests per minute
        return Bucket.builder().addLimit(limit).build();
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        if(!request.getRequestURI().contains("auth/register") && !request.getRequestURI().contains("auth/login")) {
            String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
            String jwtToken = authHeader.substring(7);
            String userName = jwtService.extractUserName(jwtToken);
            String key = userName+request.getRequestURI()+request.getMethod();
            Bucket bucket = cache.computeIfAbsent(key, k -> createNewBucket());
            if (!bucket.tryConsume(1)) {
                response.setStatus(HttpServletResponse.SC_REQUEST_URI_TOO_LONG);
                response.getWriter().write("Too many requests. Please try again later.");
                return;
            }
        }
        filterChain.doFilter(request, response);
    }
}



