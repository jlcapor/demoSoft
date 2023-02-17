package com.springboot.jpademoSof.config;

import com.springboot.jpademoSof.exceptions.AppException;
import com.springboot.jpademoSof.persistence.entity.Role;
import com.springboot.jpademoSof.persistence.entity.User;
import com.springboot.jpademoSof.persistence.repository.RoleRepository;
import com.springboot.jpademoSof.persistence.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Component
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {

    private boolean alreadySetup = false;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;


    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (alreadySetup) {
            return;
        }

        // == create initial privileges



        final Role adminRole = createRoleIfNotFound("ROLE_ADMIN");
        createRoleIfNotFound("ROLE_ADMIN");
        createRoleIfNotFound("ROLE_USER");

        // == create initial user
        createUserIfNotFound("113452345", "Dilan Matias", "Lopez Capote", "dilanmatias67@gmail.com", "3234349129",
                "r0j45_123#", true, new HashSet<>(Arrays.asList(adminRole)));

        alreadySetup = true;

    }



    @Transactional
    Role createRoleIfNotFound(final String name) {
        Role role = roleRepository.findByName(name);
        if (role == null) {
            role = new Role();
            role.setName(name);
        }
        role = roleRepository.save(role);
        return role;
    }

    @Transactional
    User createUserIfNotFound(final String cedula, final String nombre, final String apellido, final String email,
                              final String celular, final String clave, final boolean estado, final Set<Role> roles) {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            user = new User();
            user.setFirstName(nombre);
            user.setLastName(apellido);
            user.setNumberOfIdentity(cedula);
            user.setEmail(email);
            user.setNumberPhone(celular);
            user.setStatus(estado);
            user.setPassword(passwordEncoder.encode(clave));
        }
        user.setRoles(roles);
        user = userRepository.save(user);
        return user;
    }

}
