package com.springboot.jpademoSof.persistence.repository;

import com.springboot.jpademoSof.persistence.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    public Role findByName(String name);
}