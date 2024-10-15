package com.example.coursesystem.controller;

import com.example.coursesystem.dto.JwtResponseDTO;
import com.example.coursesystem.dto.user.UserAddDTO;
import com.example.coursesystem.dto.user.UserDTO;
import com.example.coursesystem.dto.user.UserLoginDTO;
import com.example.coursesystem.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<JwtResponseDTO> login(@RequestBody UserLoginDTO userLoginDTO) {
        var jwtResponseDTO = authService.login(userLoginDTO);
        return ResponseEntity.ok(jwtResponseDTO);
    }

    @PostMapping("/register")
    public ResponseEntity<UserDTO> register(@RequestBody @Valid UserAddDTO userAddDTO) {
        var user = authService.register(userAddDTO);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/logout")
    public ResponseEntity<JwtResponseDTO> logout(@RequestHeader(name = HttpHeaders.AUTHORIZATION) String token) {
        var jwtResponseDTO = authService.logout(token);
        return ResponseEntity.ok(jwtResponseDTO);
    }
}
