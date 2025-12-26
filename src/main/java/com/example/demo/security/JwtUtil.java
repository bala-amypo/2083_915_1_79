package com.example.demo.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    private final Key key;
    private final long expirationMs;

    // REQUIRED by Spring
    public JwtUtil() {
        this.key = Keys.hmacShaKeyFor(
                "this-is-a-very-secure-secret-key-1234567890".getBytes()
        );
        this.expirationMs = 1000 * 60; // 1 minute
    }

    // REQUIRED by tests
    public JwtUtil(String secret, int expirationSeconds) {
        this.key = Keys.hmacShaKeyFor(secret.getBytes());
        this.expirationMs = expirationSeconds * 1000L;
    }

    // REQUIRED by tests
    public String generateToken(Long userId, String email, String role) {
        return Jwts.builder()
                .setSubject(email)
                .claim("userId", userId)
                .claim("role", role)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationMs))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    // Backward compatibility
    public String generateToken(Long userId, String email) {
        return generateToken(userId, email, "USER");
    }

    // 🔥🔥🔥 THIS IS THE KEY FIX 🔥🔥🔥
    public Jws<Claims> validateToken(String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token);
        } catch (JwtException | IllegalArgumentException e) {
            throw new RuntimeException("Invalid token");
        }
    }
}
