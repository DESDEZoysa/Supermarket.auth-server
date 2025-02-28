package com.eranga.supermarket.auth_server.service;

import com.eranga.supermarket.auth_server.model.dto.AppUserDto;
import com.eranga.supermarket.auth_server.model.dto.AuthenticationDto;
import jakarta.servlet.http.HttpServletRequest;

public interface AuthenticationService {

    AuthenticationDto register(AppUserDto appUserDto);
    AuthenticationDto authenticate(AppUserDto appUserDto);
    AuthenticationDto refreshToken(HttpServletRequest request);
}
