package com.eranga.supermarket.auth_server.model.mapper;

import com.eranga.supermarket.auth_server.model.dto.AppUserDto;
import com.eranga.supermarket.auth_server.model.dto.AuthenticationDto;
import com.eranga.supermarket.auth_server.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthenticationDtoMapper {

    private final JwtService jwtService;

    public AuthenticationDto mapAuthenticationDto(AppUserDto appUserDto){
        return AuthenticationDto.builder()
                .accessToken(jwtService.generateAccessToken(appUserDto))
                .build();
    }
}
