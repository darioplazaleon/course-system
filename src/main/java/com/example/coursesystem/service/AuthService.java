package com.example.coursesystem.service;

import com.example.coursesystem.dto.JwtResponseDTO;
import com.example.coursesystem.dto.UserAddDTO;
import com.example.coursesystem.dto.UserDTO;
import com.example.coursesystem.dto.UserLoginDTO;

import java.util.Optional;

public interface AuthService {
    JwtResponseDTO login(UserLoginDTO userLoginDTO);
    JwtResponseDTO logout(String token);

    UserDTO register(UserAddDTO userAddDTO);
}
