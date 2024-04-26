package com.kopiyama.ebdeskapp.repository;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

public class AuthRepository {

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AuthRequest {
        private String username;
        private String password;
    }

    @Value
    public static class AuthResponse {
        String token;
    }
}
