package com.example.demo.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component   // 🔥 THIS WAS MISSING
public class JwtUtil {

    private final Key key;
    private final long expirationMs;

    // ✅ REQUIRED BY TESTS
    public JwtUtil() {
        this.key = Keys.hmacShaKeyFor(
                "this-is-a-very-secure-secret-key-1234567890".getBytes()
        );
        this.expirationMs = 1000 * 60; // 1 minute (tests expect expiration)
    }

    // ✅ USED BY TESTS
    public JwtUtil(String secret, int expirationSeconds) {
        this.key = Keys.hmacShaKeyFor(secret.getBytes());
        this.expirationMs = expirationSeconds * 1000L;
    }

    // ✅ TEST EXPECTS (userId + email + role)
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

    // ✅ BACKWARD COMPAT (some tests call this)
    public String generateToken(Long userId, String email) {
        return generateToken(userId, email, "USER");
    }

    // ✅ VALIDATION
    public Claims validateToken(String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (JwtException | IllegalArgumentException e) {
            throw new RuntimeException("Invalid token");
        }
    }
}
