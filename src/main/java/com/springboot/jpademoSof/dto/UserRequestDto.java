package com.springboot.jpademoSof.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.io.Serializable;

/**
 * A DTO for the {@link com.springboot.jpademoSof.persistence.entity.User} entity
 */
@Data
public class UserRequestDto implements Serializable {
    private final Long id;
    @Size(max = 255)
    @NotNull
    private final String email;
    @Size(max = 255)
    private final String firstName;
    @Size(max = 255)
    private final String lastName;
    @Size(max = 255)
    private final String numberOfIdentity;
    @Size(max = 255)
    private final String password;
    @Size(max = 255)
    private final String numberPhone;
    @Size(max = 255)
    private final String photo;

    
}