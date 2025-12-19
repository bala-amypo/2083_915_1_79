package com.example.demo.repository;

import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

// Repository for User entity
// JpaRepository gives basic CRUD operations automatically
public interface UserRepository extends JpaRepository<User, Long> {

    // Custom method to find user by email
    Optional<User> findByEmail(String email);
}
