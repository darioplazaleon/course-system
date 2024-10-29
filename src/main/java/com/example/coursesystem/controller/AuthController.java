package com.example.coursesystem.controller;

import com.example.coursesystem.dto.JwtResponseDTO;
import com.example.coursesystem.dto.user.UserAddDTO;
import com.example.coursesystem.dto.user.UserDTO;
import com.example.coursesystem.dto.user.UserLoginDTO;
import com.example.coursesystem.service.AuthService;
import com.example.coursesystem.service.impl.IAuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final IAuthService iAuthService;

    @PostMapping("/login")
    public ResponseEntity<JwtResponseDTO> login(@RequestBody UserLoginDTO userLoginDTO) {
        var jwtResponseDTO = iAuthService.login(userLoginDTO);
        return ResponseEntity.ok(jwtResponseDTO);
    }

    @PostMapping("/register")
    public ResponseEntity<UserDTO> register(@RequestBody @Valid UserAddDTO userAddDTO) {
        var user = iAuthService.register(userAddDTO);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/logout")
    public ResponseEntity<JwtResponseDTO> logout(@RequestHeader(name = HttpHeaders.AUTHORIZATION) String token) {
        var jwtResponseDTO = iAuthService.logout(token);
        return ResponseEntity.ok(jwtResponseDTO);
    }
}
