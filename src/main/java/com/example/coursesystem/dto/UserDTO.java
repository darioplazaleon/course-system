package com.example.coursesystem.dto;

import com.example.coursesystem.entity.Role;
import com.example.coursesystem.entity.User;

public record UserDTO(
        Long id,
        String name,
        String email,
        Role role
) {
    public UserDTO(User user) {
        this(user.getId(), user.getName(), user.getEmail(), user.getRole());
    }
}
