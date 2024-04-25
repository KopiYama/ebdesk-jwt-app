package com.kopiyama.ebdeskapp.controller;

import com.kopiyama.ebdeskapp.model.User;
import com.kopiyama.ebdeskapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/api/v1/registration")
    public String registerUser(@RequestBody User user) {
        if (userRepository.findByUsername(user.getUsername()) !=null) {
            return "Username already exists";
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return "User registered successfully";
    }

}
