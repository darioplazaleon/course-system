package com.example.coursesystem.service.impl;

import com.example.coursesystem.dto.UserDTO;
import com.example.coursesystem.repository.UserRepository;
import com.example.coursesystem.security.JwtAuthenticationProvider;
import com.example.coursesystem.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class IUserService implements UserService {

    private final UserRepository userRepository;
    private final JwtAuthenticationProvider jwtAuthenticationProvider;

    @Override
    public UserDTO getUserById(Long id) {
        return userRepository.findById(id)
                .map(UserDTO::new)
                .orElseThrow(() -> new IllegalArgumentException("User with id " + id + " not found"));
    }

    @Override
    public UserDTO getMe(String jwtToken) {
        System.out.println("Entering getMe");
        System.out.println("Token received: " + jwtToken);

        if (jwtToken.startsWith("Bearer ")) {
            jwtToken = jwtToken.substring(7);
        } else {
            throw new IllegalArgumentException("Invalid Authorization header");
        }

        Long userId = jwtAuthenticationProvider.getUserId(jwtToken);
        System.out.println("User id: " + userId);
        return userRepository.findById(userId)
                .map(UserDTO::new)
                .orElseThrow(() -> new IllegalArgumentException("User with id " + userId + " not found"));
    }
}
