package com.kopiyama.ebdeskapp.controller;

import com.kopiyama.ebdeskapp.model.User;
import com.kopiyama.ebdeskapp.repository.UserRepository;
import com.kopiyama.ebdeskapp.service.RegistrationRequest;
import jakarta.validation.Valid;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Getter
@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/api/v1/registration")
    public ResponseEntity<?> registerUser(@RequestBody @Valid RegistrationRequest registrationRequest, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult.getFieldError().getDefaultMessage());
        }

        if (userRepository.findByUsername(registrationRequest.getUsername()) != null) {
            return ResponseEntity.badRequest().body("Username already exists");
        }

        User user = new User();
        user.setUsername(registrationRequest.getUsername());
        user.setPassword(passwordEncoder.encode(registrationRequest.getPassword()));
        user.setEmail(registrationRequest.getEmail());
        userRepository.save(user);
        return ResponseEntity.ok("User registered successfully");
    }
//    public String registerUser(@RequestBody User user) {
//        if (userRepository.findByUsername(user.getUsername()) !=null) {
//            return "Username already exists";
//        }
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
//        userRepository.save(user);
//        return "User registered successfully";
//    }

}
