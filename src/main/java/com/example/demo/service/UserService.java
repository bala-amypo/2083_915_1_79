package com.example.demo.service;

import com.example.demo.entity.User;
@Service
// Service interface for User related operations
public interface UserService {

    // Register a new user
    User register(User user);

    // Find user by email
    User findByEmail(String email);

    // Find user by id
    User findById(Long id);
}
