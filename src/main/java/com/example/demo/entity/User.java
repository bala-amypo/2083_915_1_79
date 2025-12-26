package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users") // ✅ FIX: avoid reserved keyword
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class User {
    @Id @GeneratedValue
    private Long id;
    private String name;
    private String email;
    private String password;   // ✅ add this
    private String role;

    // getters and setters
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}
