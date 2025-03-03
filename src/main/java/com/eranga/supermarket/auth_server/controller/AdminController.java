package com.eranga.supermarket.auth_server.controller;

import com.eranga.supermarket.auth_server.model.entity.SecurityDetailsEntity;
import com.eranga.supermarket.auth_server.repository.SecurityDetailsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("admin")
@RequiredArgsConstructor
public class AdminController {

    private final SecurityDetailsRepository securityDetailsRepository;

    @GetMapping()
    public String getAdmin(){
        return "Get Admin";
    }

    @GetMapping("/hello")
    public String hello(){
        return "Hello Admin";
    }

    @PostMapping()
    public String postAdmin(){
        return "Post Admin";
    }

    @PostMapping("/addSecurityDetails")
    public String AddSecurityDetails(){
        securityDetailsRepository.save(SecurityDetailsEntity.builder().key("scwfedfwe").build());
        return "Post AddSecurityDetails";
    }

    @PutMapping()
    public String putAdmin(){
        return "Put Admin";
    }

    @DeleteMapping()
    public String deleteAdmin(){
        return "Delete Admin";
    }
}
