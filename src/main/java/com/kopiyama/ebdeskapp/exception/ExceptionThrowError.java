package com.kopiyama.ebdeskapp.exception;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

public class ExceptionThrowError {

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AuthRequest {
        @NotBlank(message = "Username is empty")
        private String username;

        @NotBlank(message = "Password is empty")
        private String password;

        @NotBlank(message = "Email is empty")
        @Email(message = "Email must be a valid email address")
        private String email;  // Merging RegistrationRequest into AuthRequest
    }

    @Value
    public static class AuthResponse {
        String token;
    }
}
