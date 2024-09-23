package com.example.coursesystem.service.impl;

import com.example.coursesystem.config.JwtAuthenticationProvider;
import com.example.coursesystem.dto.JwtResponseDTO;
import com.example.coursesystem.dto.UserAddDTO;
import com.example.coursesystem.dto.UserDTO;
import com.example.coursesystem.dto.UserLoginDTO;
import com.example.coursesystem.entity.User;
import com.example.coursesystem.exception.EmailValidationException;
import com.example.coursesystem.repository.UserRepository;
import com.example.coursesystem.service.AuthService;
import jakarta.persistence.EntityExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class IAuthService implements AuthService {

    private final UserRepository userRepository;

    private final JwtAuthenticationProvider jwtAuthenticationProvider;

    private final PasswordEncoder passwordEncoder;


    @Override
    public JwtResponseDTO login(UserLoginDTO userLoginDTO) {
        Optional<User> user = userRepository.findByEmail(userLoginDTO.email());

        if (user.isEmpty()) {
            throw new EntityExistsException("User with email " + userLoginDTO.email() + " does not exist");
        }

        if (!passwordEncoder.matches(userLoginDTO.password(), user.get().getPassword())) {
            throw new EntityExistsException("Invalid password");
        }

        return new JwtResponseDTO(jwtAuthenticationProvider.createToken(new UserDTO(user.get())));
    }

    @Override
    public JwtResponseDTO logout(String token) {
        String[] parts = token.split(" ");
        return new JwtResponseDTO(jwtAuthenticationProvider.deleteToken(parts[1]));
    }

    @Override
    public UserDTO register(UserAddDTO userAddDTO) {
        if (!userAddDTO.email().matches("^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$")) {
            throw new EmailValidationException();
        }

        System.out.println("Email: " + userAddDTO.email());
        System.out.println(userRepository.findByEmail(userAddDTO.email()));

        if (userRepository.findByEmail(userAddDTO.email()).isPresent()) {
            throw new EntityExistsException("User with email " + userAddDTO.email() + " already exists");
        }

        String password = passwordEncoder.encode(userAddDTO.password());

        var user = new User(userAddDTO, password);

        userRepository.save(user);

        return new UserDTO(user);
    }
}
