package com.example.coursesystem.service;

import com.example.coursesystem.dto.course.CourseDTO;
import com.example.coursesystem.dto.user.UserDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface UserService {
    UserDTO getUserById(Long id);
    UserDTO getMe(String jwtToken);
    void addFavoriteCourse (String jwtToken, Long courseId);
    void removeFavoriteCourse (String jwtToken, Long courseId);
    List<CourseDTO> getFavoriteCourses(String jwtToken);
}
