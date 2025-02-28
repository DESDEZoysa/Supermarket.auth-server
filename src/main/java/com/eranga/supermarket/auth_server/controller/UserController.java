package com.eranga.supermarket.auth_server.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
@RequiredArgsConstructor
public class UserController {

    @GetMapping()
    public String getUser(){
        return "Get User";
    }

    @GetMapping("/hello")
    public String hello(){
        return "Hello User";
    }

    @PostMapping()
    public String postUser(){
        return "Post User";
    }

    @DeleteMapping()
    public String deleteUser(){
        return "Delete User";
    }
}
