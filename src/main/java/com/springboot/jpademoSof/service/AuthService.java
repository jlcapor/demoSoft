package com.springboot.jpademoSof.service;

import com.springboot.jpademoSof.dto.JwtAuthenticationResponse;
import com.springboot.jpademoSof.dto.UserRequestDto;
import com.springboot.jpademoSof.dto.UserResponseDto;
import com.springboot.jpademoSof.jwt.JwtTokenProvider;
import com.springboot.jpademoSof.persistence.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthService {
    private final AuthenticationManagerBuilder managerBuilder;
    private final JwtTokenProvider jwtTokenProvider;

    public JwtAuthenticationResponse login(UserResponseDto requestDto){
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(requestDto.getEmail(), requestDto.getPassword());
        Authentication authentication = managerBuilder.getObject().authenticate(authenticationToken);
        return jwtTokenProvider.generateToken(authentication);
    }
}
