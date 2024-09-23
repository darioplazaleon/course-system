package com.example.coursesystem.controller;

import com.example.coursesystem.dto.UserAddDTO;
import com.example.coursesystem.dto.UserDTO;
import com.example.coursesystem.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/register")
    public ResponseEntity<UserDTO> register(@RequestBody @Valid UserAddDTO userAddDTO) {
        var user = authService.register(userAddDTO);
        return ResponseEntity.ok(user);
    }
}
