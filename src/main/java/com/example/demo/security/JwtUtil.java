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

    // REQUIRED by Spring
    public JwtUtil() {
    this.key = Keys.hmacShaKeyFor(
            "this-is-a-very-secure-secret-key-1234567890".getBytes()
    );
    this.expirationMs = 1000; // 🔥 1 second ONLY
}


    // REQUIRED by tests
    public JwtUtil(String secret, int expirationSeconds) {
        this.key = Keys.hmacShaKeyFor(secret.getBytes());
        this.expirationMs = expirationSeconds * 1000L;
    }

    // REQUIRED by tests
   public String generateToken(User user) {
    return Jwts.builder()
            .setSubject(user.getEmail())   // 🔥 REQUIRED
            .claim("userId", user.getId())
            .claim("role", user.getRole())
            .setIssuedAt(new Date())
            .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
            .signWith(secretKey, SignatureAlgorithm.HS256)
            .compact();
}



    // Backward compatibility
    public String generateToken(Long userId, String email) {
        return generateToken(userId, email, "USER");
    }

    // 🔥🔥🔥 THIS IS THE KEY FIX 🔥🔥🔥
   public boolean validateToken(String token) {
    try {
        Jwts.parserBuilder()
            .setSigningKey(secretKey)
            .build()
            .parseClaimsJws(token);
        return true;
    } catch (Exception e) {
        return false;   // 🔥 REQUIRED
    }
}

}
