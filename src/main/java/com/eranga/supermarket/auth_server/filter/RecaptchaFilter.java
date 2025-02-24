package com.eranga.supermarket.auth_server.filter;

import com.eranga.supermarket.auth_server.model.dto.RecaptchaDto;
import com.eranga.supermarket.auth_server.service.RecaptchaService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;


@Component
@RequiredArgsConstructor
public class RecaptchaFilter extends OncePerRequestFilter {

    private final RecaptchaService recaptchaService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if(request.getRequestURI().contains("auth/register")) {
            String recaptcha = request.getHeader("recaptcha");
            RecaptchaDto recaptchaResponse = recaptchaService.validateToken(recaptcha);
            if(!recaptchaResponse.getSuccess()) {
                response.setStatus(HttpServletResponse.SC_REQUEST_URI_TOO_LONG);
                response.getWriter().write("Invalid reCaptcha token");
                return;
            }
        }
        filterChain.doFilter(request,response);
    }

}
