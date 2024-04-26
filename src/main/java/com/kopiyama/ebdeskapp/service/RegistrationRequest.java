package com.kopiyama.ebdeskapp.service;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RegistrationRequest {
    @NotBlank(message = "Username is empty")
    private String username;

    @NotBlank(message = "Password is empty")
    private String password;

    @NotBlank(message = "Email is empty")
    @Email(message = "Email must be a valid email address")
    private String email;
}
