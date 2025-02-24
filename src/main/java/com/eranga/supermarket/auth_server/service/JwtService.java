package com.eranga.supermarket.auth_server.service;

import org.springframework.security.core.userdetails.UserDetails;

import java.security.NoSuchAlgorithmException;

public interface JwtService {

    String generateAccessToken(String username);
    String extractUserName(String token);
    Boolean isJwtTokenValid(String jwtToken, UserDetails userDetails);
}
