package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.dto.AuthRequest;
import com.example.demo.dto.AuthResponse;
import com.example.demo.dto.RegisterRequest;
import com.example.demo.service.UserAccountService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserAccountService userAccountService;

    @PostMapping("/register")
    public String register(@RequestBody RegisterRequest request) {
        userAccountService.register(request);
        return "User registered successfully";
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthRequest request) {
        return userAccountService.login(request);
    }
}



