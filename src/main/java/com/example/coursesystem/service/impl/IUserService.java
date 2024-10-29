package com.example.coursesystem.service.impl;

import com.example.coursesystem.dto.course.CourseDTO;
import com.example.coursesystem.dto.user.UserDTO;
import com.example.coursesystem.entity.Course;
import com.example.coursesystem.entity.User;
import com.example.coursesystem.repository.CourseRepository;
import com.example.coursesystem.repository.UserRepository;
import com.example.coursesystem.security.JwtAuthenticationProvider;
import com.example.coursesystem.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class IUserService implements UserService {

    private final UserRepository userRepository;
    private final JwtAuthenticationProvider jwtAuthenticationProvider;
    private final CourseRepository courseRepository;

    @Override
    public UserDTO getUserById(Long id) {
        return userRepository.findById(id)
                .map(UserDTO::new)
                .orElseThrow(() -> new IllegalArgumentException("User with id " + id + " not found"));
    }

    @Override
    public UserDTO getMe(String jwtToken) {
        Long userId = jwtAuthenticationProvider.getUserId(jwtToken);
        System.out.println("User id: " + userId);
        return userRepository.findById(userId)
                .map(UserDTO::new)
                .orElseThrow(() -> new IllegalArgumentException("User with id " + userId + " not found"));
    }

    @Override
    @Transactional
    public void addFavoriteCourse(String jwtToken, Long courseId) {
        Long userId = jwtAuthenticationProvider.getUserId(jwtToken);

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User with id " + userId + " not found"));

        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new IllegalArgumentException("Course with id " + courseId + " not found"));
        user.getFavoriteCourses().add(course);
        userRepository.save(user);
    }

    @Override
    @Transactional
    public void removeFavoriteCourse(String jwtToken, Long courseId) {
        Long userId = jwtAuthenticationProvider.getUserId(jwtToken);

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User with id " + userId + " not found"));

        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new IllegalArgumentException("Course with id " + courseId + " not found"));
        user.getFavoriteCourses().remove(course);
        userRepository.save(user);
    }

    @Override
    public List<CourseDTO> getFavoriteCourses(String jwtToken) {
        Long userId = jwtAuthenticationProvider.getUserId(jwtToken);

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User with id " + userId + " not found"));

        Set<Course> favoriteCourses = user.getFavoriteCourses();

        return favoriteCourses.stream().map(CourseDTO::new).toList();
    }
}
