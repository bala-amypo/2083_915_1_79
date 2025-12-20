package com.example.demo.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;

public class JwtUtil {

    public JwtUtil(String secret, long expirationMs) {
    }

    public String generateToken(Long userId, String email, String role) {
        return null;
    }

    public Jws<Claims> validateToken(String token) {
        throw new RuntimeException("Invalid token");
    }
}
