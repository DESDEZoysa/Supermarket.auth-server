package com.eranga.supermarket.auth_server.controller;

import com.eranga.supermarket.auth_server.model.dto.LoginDetailsDto;
import com.eranga.supermarket.auth_server.model.dto.AppUserDto;
import com.eranga.supermarket.auth_server.model.dto.AuthenticationDto;
import com.eranga.supermarket.auth_server.service.AuthenticationService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    private List<String> itemList = new ArrayList<>();

    @GetMapping
    public List<String> getItemList(){
        return itemList;
    }

    @GetMapping("/SRFToken")
    public CsrfToken getCSRFToken(HttpServletRequest request){
        return (CsrfToken) request.getAttribute("_csrf");
    }

    @PostMapping
    public String addItem(@RequestBody String item){
        this.itemList.add(item);
        return item;
    }


    @PostMapping("/register")
    public AuthenticationDto register(@RequestBody AppUserDto appUserDto) {
        return this.authenticationService.register(appUserDto);
    }

    @PostMapping("/login")
    public AuthenticationDto authenticate(@RequestBody LoginDetailsDto loginDetailsDto) {
        return authenticationService.authenticate(loginDetailsDto);
    }
}
