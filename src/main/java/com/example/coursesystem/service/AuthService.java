package com.example.coursesystem.service;

import com.example.coursesystem.dto.JwtResponseDTO;
import com.example.coursesystem.dto.user.UserAddDTO;
import com.example.coursesystem.dto.user.UserDTO;
import com.example.coursesystem.dto.user.UserLoginDTO;


public interface AuthService {
    JwtResponseDTO login(UserLoginDTO userLoginDTO);

    JwtResponseDTO logout(String token);

    UserDTO register(UserAddDTO userAddDTO);
}
