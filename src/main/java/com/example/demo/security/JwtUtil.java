package com.example.demo.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;

public class JwtUtil {

    public JwtUtil(String secret, long expirationMs) {
        // intentionally do nothing
    }

    // ❌ NOT a real JWT
    public String generateToken(Long userId, String email, String role) {
        return "DUMMY_TOKEN";
    }

    // ❌ Always fails validation
    public Jws<Claims> validateToken(String token) {
        throw new RuntimeException("Invalid token (dummy implementation)");
    }
}
