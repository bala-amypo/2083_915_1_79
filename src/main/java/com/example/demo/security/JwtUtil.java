package com.example.demo.security;

import com.example.demo.entity.User;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    private final Key key;
    private final long expirationMs;

    // Default constructor for Spring
    public JwtUtil() {
        this.key = Keys.hmacShaKeyFor(
                "this-is-a-very-secure-secret-key-1234567890".getBytes()
        );
        this.expirationMs = 60 * 60 * 1000; // 1 hour
    }

    // Constructor used by tests
    public JwtUtil(String secret, int expirationSeconds) {
        this.key = Keys.hmacShaKeyFor(secret.getBytes());
        this.expirationMs = expirationSeconds * 1000L;
    }

    // 🔥 MAIN TOKEN GENERATOR (TESTED)
    public String generateToken(User user) {
        return Jwts.builder()
                .setSubject(user.getEmail())              // ✅ REQUIRED
                .claim("userId", user.getId())
                .claim("role", user.getRole())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationMs))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    // Backward compatibility
    public String generateToken(Long userId, String email) {
        return Jwts.builder()
                .setSubject(email)                        // ✅ REQUIRED
                .claim("userId", userId)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationMs))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    // 🔥 VALIDATE + RETURN CLAIMS
    public Claims extractClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public boolean validateToken(String token) {
        try {
            extractClaims(token);
            return true;
        } catch (JwtException e) {
            return false;
        }
    }
}
