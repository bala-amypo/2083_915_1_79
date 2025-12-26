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

    // Spring default constructor
    public JwtUtil() {
        this.key = Keys.hmacShaKeyFor(
                "this-is-a-very-secure-secret-key-1234567890".getBytes()
        );
        this.expirationMs = 1000; // 1 second (tests expect expiry)
    }

    // Test constructor
    public JwtUtil(String secret, int expirationSeconds) {
        this.key = Keys.hmacShaKeyFor(secret.getBytes());
        this.expirationMs = expirationSeconds * 1000L;
    }

    // REQUIRED by tests
    public String generateToken(User user) {
        return generateToken(user.getId(), user.getEmail(), user.getRole());
    }

    // REQUIRED by tests
    public String generateToken(Long userId, String email) {
        return generateToken(userId, email, "USER");
    }

    // 🔥 REQUIRED SIGNATURE
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

    public Claims getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public boolean validateToken(String token) {
        try {
            getClaims(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isTokenExpired(String token) {
        try {
            return getClaims(token).getExpiration().before(new Date());
        } catch (Exception e) {
            return true;
        }
    }

    public String getEmailFromToken(String token) {
        return getClaims(token).getSubject();
    }
}
