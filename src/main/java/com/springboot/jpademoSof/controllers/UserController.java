package com.springboot.jpademoSof.controllers;


import com.springboot.jpademoSof.dto.UserProfile;
import com.springboot.jpademoSof.dto.UserSummary;
import com.springboot.jpademoSof.persistence.entity.User;
import com.springboot.jpademoSof.persistence.repository.UserRepository;
import com.springboot.jpademoSof.jwt.CurrentUser;
import com.springboot.jpademoSof.jwt.UserPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/me")
    public UserSummary getCurrentUser(@CurrentUser UserPrincipal currentUser) {
        UserSummary userSummary = new UserSummary(currentUser.getId(), currentUser.getUsername(), currentUser.getFirstName(), currentUser.getLastName());
        return userSummary;
    }

    @GetMapping("/{username}")
    public UserProfile getUserProfile(@PathVariable(value = "username") String username) {
        User user = userRepository.findByEmail(username);
        UserProfile userProfile = new UserProfile(user.getId(), user.getEmail(), user.getFirstName(), user.getLastName(), user.getNumberPhone(), user.getPhoto());
        return userProfile;
    }



}
