package com.springboot.jpademoSof.controllers;

import com.springboot.jpademoSof.dto.UserResponseDto;
import com.springboot.jpademoSof.jwt.JwtTokenProvider;
import com.springboot.jpademoSof.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationController {


    private final AuthService authService;

    private final JwtTokenProvider tokenProvider;

    public AuthenticationController(AuthService authService, JwtTokenProvider tokenProvider) {
        this.authService = authService;
        this.tokenProvider = tokenProvider;
    }


    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody UserResponseDto loginRequest) {
        return ResponseEntity.ok(authService.login(loginRequest));
    }


}
