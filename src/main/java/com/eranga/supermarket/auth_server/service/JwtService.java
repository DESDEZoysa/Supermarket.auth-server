package com.eranga.supermarket.auth_server.service;

import com.eranga.supermarket.auth_server.model.dto.AppUserDto;
import org.springframework.security.core.userdetails.UserDetails;

import java.security.NoSuchAlgorithmException;

public interface JwtService {

    String generateAccessToken(AppUserDto appUserDto);
    String extractUserName(String token);
    Boolean isJwtTokenValid(String jwtToken, UserDetails userDetails);
}
