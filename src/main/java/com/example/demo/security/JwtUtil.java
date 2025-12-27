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
        this("this-is-a-very-secure-secret-key-1234567890", 3600);
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

    // REQUIRED
    public Claims validateToken(String token) {
        return extractAllClaims(token);
    }

    // Used internally & by filter
    public Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    // Backward compatibility
    public Claims extractClaims(String token) {
        return extractAllClaims(token);
    }
}
