package com.example.coursesystem.service;

import com.example.coursesystem.dto.user.UserDTO;

public interface UserService {
    UserDTO getUserById(Long id);
    UserDTO getMe(String jwtToken);
}
