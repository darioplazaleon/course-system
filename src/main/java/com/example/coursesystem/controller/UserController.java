package com.example.coursesystem.controller;

import com.example.coursesystem.dto.course.CourseDTO;
import com.example.coursesystem.dto.user.UserDTO;
import com.example.coursesystem.service.impl.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {

    private final IUserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @GetMapping("/me")
    public ResponseEntity<UserDTO> getCurrentUser(@RequestHeader("Authorization") String jwtToken) {
        return ResponseEntity.ok(userService.getMe(jwtToken));
    }

    @PostMapping("/favorite-courses/{courseId}")
    public ResponseEntity<Void> addFavoriteCourse(@RequestHeader("Authorization") String jwtToken, @PathVariable long courseId) {
        String token = jwtToken.split(" ")[1];
        userService.addFavoriteCourse(token, courseId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/me/favorites")
    public ResponseEntity<List<CourseDTO>> getFavoriteCourses(@RequestHeader("Authorization") String jwtToken) {
        String token = jwtToken.split(" ")[1];
        return ResponseEntity.ok(userService.getFavoriteCourses(token));
    }
}
