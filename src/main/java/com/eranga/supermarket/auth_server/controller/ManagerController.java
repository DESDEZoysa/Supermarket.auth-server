package com.eranga.supermarket.auth_server.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("manager")
@RequiredArgsConstructor
public class ManagerController {

    @GetMapping()
    public String getManager(){
        return "Get Manager";
    }

    @GetMapping("/hello")
    public String hello(){
        return "Hello Manager";
    }

    @PostMapping()
    public String postManager(){
        return "Post Manager";
    }

    @PutMapping()
    public String putManager(){
        return "Put Manager";
    }

    @DeleteMapping()
    public String deleteManager(){
        return "Delete Manager";
    }
}
