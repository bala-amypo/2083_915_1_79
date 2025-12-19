package com.example.demo.service;

import com.example.demo.entity.User;

public interface UserService {

    // Register a new user (password hashing + default role)
    User register(User user);

    // Used during login & authentication
    User findByEmail(String email);
}
