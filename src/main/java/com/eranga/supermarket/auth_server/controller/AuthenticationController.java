package com.eranga.supermarket.auth_server.controller;

import com.eranga.supermarket.auth_server.model.dto.AppUserDto;
import com.eranga.supermarket.auth_server.model.dto.AuthenticationDto;
import com.eranga.supermarket.auth_server.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public AuthenticationDto register(@RequestBody AppUserDto appUserDto) {
        return this.authenticationService.register(appUserDto);
    }

    @PostMapping("/login")
    public AuthenticationDto authenticate(@RequestBody AppUserDto appUserDto) {
        return authenticationService.authenticate(appUserDto);
    }
}
