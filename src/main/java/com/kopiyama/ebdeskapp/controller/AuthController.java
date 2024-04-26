package com.kopiyama.ebdeskapp.controller;

import com.kopiyama.ebdeskapp.config.JwtTokenProvider;
import com.kopiyama.ebdeskapp.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider tokenProvider;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody AuthService.AuthRequest loginRequest) {
        try {
            // Create an authentication token
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword());

            // Authenticate user
            Authentication authentication = authenticationManager.authenticate(authenticationToken);

            // Set authentication in security context
            SecurityContextHolder.getContext().setAuthentication(authentication);

            // Generate JWT token
            String jwt = tokenProvider.generateToken(authentication.getName()); // Ensure this call matches your JwtTokenProvider

            // Return the token
            return ResponseEntity.ok(new AuthService.AuthResponse(jwt));
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body("Authentication failed: " + ex.getMessage());
        }
    }
}
