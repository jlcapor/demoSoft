package com.springboot.jpademoSof.service;

import com.springboot.jpademoSof.exceptions.ResourceNotFoundException;
import com.springboot.jpademoSof.jwt.UserPrincipal;
import com.springboot.jpademoSof.persistence.entity.User;
import com.springboot.jpademoSof.persistence.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {


    private final UserRepository userRepository;
    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        final User user = userRepository.findByEmail(email);
        System.out.println(user.getFirstName() + "444444444444444");
        return UserPrincipal.create(user);
    }

    @Transactional
    public UserDetails loadUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "id", id));

        return UserPrincipal.create(user);
    }
}
