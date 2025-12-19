package com.example.demo.service.impl;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Override
    public User createUser(User user) {
        // Dummy behavior: return same user with fake ID
        user.setId(1L);
        return user;
    }

    @Override
    public User getUserById(Long id) {
        // Dummy user
        return User.builder()
                .id(id)
                .username("test_user")
                .password("password")
                .build();
    }

    @Override
    public List<User> getAllUsers() {
        // Return non-null list (VERY IMPORTANT)
        List<User> users = new ArrayList<>();
        users.add(
                User.builder()
                        .id(1L)
                        .username("user1")
                        .password("pass")
                        .build()
        );
        return users;
    }
}
