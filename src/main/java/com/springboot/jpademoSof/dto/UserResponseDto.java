package com.springboot.jpademoSof.dto;

import jakarta.validation.constraints.NotBlank;

import java.io.Serializable;


public class UserResponseDto implements Serializable {
    @NotBlank
    private String email;
    @NotBlank
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}