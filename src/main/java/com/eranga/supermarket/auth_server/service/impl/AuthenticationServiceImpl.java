package com.eranga.supermarket.auth_server.service.impl;

import com.eranga.supermarket.auth_server.model.dto.LoginDetailsDto;
import com.eranga.supermarket.auth_server.model.dto.AppUserDto;
import com.eranga.supermarket.auth_server.model.dto.AuthenticationDto;
import com.eranga.supermarket.auth_server.model.mapper.AppUserMapper;
import com.eranga.supermarket.auth_server.model.mapper.AuthenticationDtoMapper;
import com.eranga.supermarket.auth_server.repository.AppUserRepository;
import com.eranga.supermarket.auth_server.service.AuthenticationService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(10);
    private final AppUserMapper appUserMapper;
    private final AppUserRepository appUserRepository;
    private final AuthenticationManager authenticationManager;
    private final AuthenticationDtoMapper authenticationDtoMapper;
    private final JwtServiceImpl jwtService;

    @Override
    public AuthenticationDto register(AppUserDto appUserDto) {
        appUserDto.setPassword(this.bCryptPasswordEncoder.encode(appUserDto.getPassword()));
        this.appUserRepository.save(appUserMapper.mapDtoToEntity(appUserDto));
        return this.authenticationDtoMapper.mapAuthenticationDto(appUserDto.getUserName());
    }

    @Override
    public AuthenticationDto authenticate(LoginDetailsDto loginDetailsDto) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDetailsDto.getUserName(),loginDetailsDto.getPassword()));
        return this.authenticationDtoMapper.mapAuthenticationDto(loginDetailsDto.getUserName());
    }

    @Override
    public AuthenticationDto refreshToken(HttpServletRequest request) {
        return null;
    }
}
