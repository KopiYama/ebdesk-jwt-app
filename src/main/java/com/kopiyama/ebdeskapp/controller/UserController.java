package com.kopiyama.ebdeskapp.controller;

import com.kopiyama.ebdeskapp.jwt.JwtTokenProvider;
import com.kopiyama.ebdeskapp.model.User;
import com.kopiyama.ebdeskapp.repository.UserRepository;
import com.kopiyama.ebdeskapp.exception.ExceptionThrowError;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/auth")
public class UserController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider tokenProvider;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody ExceptionThrowError.AuthRequest loginRequest) {
        try {
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword());
            Authentication authentication = authenticationManager.authenticate(authenticationToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = tokenProvider.generateToken(authentication.getName());

            return ResponseEntity.ok(new ExceptionThrowError.AuthResponse(jwt));
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body("Username or password is incorrect");
        }
    }

    @PostMapping("/registration")
    public ResponseEntity<?> registerUser(@RequestBody @Valid ExceptionThrowError.AuthRequest registrationRequest, BindingResult bindingResult) {
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

    @GetMapping("/resource")
    @PreAuthorize("isAuthenticated()") // Ensure the user is authenticated
    public ResponseEntity<String> getProtectedResource(Authentication authentication) {
        if (authentication == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authentication is required");
        }

        String username = authentication.getName();  // Sekarang ini aman karena kita sudah cek null
        User user = userRepository.findByUsername(username);
        if (user != null) {
            return ResponseEntity.ok(user.getEmail());  // Mengembalikan email pengguna
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
    }
}
