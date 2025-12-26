package com.example.demo.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.Map;

@Component
public class JwtUtil {

    private static final String SECRET = "mysecretkeymysecretkeymysecretkey123"; // at least 32 chars
    private static final long EXPIRATION = 1000 * 60 * 60; // 1 hour

    private final Key key = Keys.hmacShaKeyFor(SECRET.getBytes());

    public String generateToken(String email, Long userId, String role) {
        return Jwts.builder()
                .setSubject(email)
                .addClaims(Map.of(
                        "userId", userId,
                        "role", role
                ))
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (JwtException e) {
            return false; // ✅ don’t throw, return false so tests can assert
        }
    }

    public Claims extractClaims(String token) {
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
    }

    public String extractRole(String token) {
        return extractClaims(token).get("role", String.class);
    }

    public Long extractUserId(String token) {
        return extractClaims(token).get("userId", Long.class);
    }
}
