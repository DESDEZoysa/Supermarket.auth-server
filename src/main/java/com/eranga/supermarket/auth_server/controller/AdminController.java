package com.eranga.supermarket.auth_server.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("admin")
@RequiredArgsConstructor
public class AdminController {

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

    @DeleteMapping()
    public String deleteAdmin(){
        return "Delete Admin";
    }
}
