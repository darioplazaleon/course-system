package com.example.coursesystem.service.impl;

import com.example.coursesystem.dto.enrollment.EnrollmentDTO;
import com.example.coursesystem.entity.Course;
import com.example.coursesystem.entity.Enrollment;
import com.example.coursesystem.entity.User;
import com.example.coursesystem.repository.CourseRepository;
import com.example.coursesystem.repository.EnrollmentRepository;
import com.example.coursesystem.repository.UserRepository;
import com.example.coursesystem.security.JwtAuthenticationProvider;
import com.example.coursesystem.service.EnrollmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class IEnrollmentService implements EnrollmentService {

  private final UserRepository userRepository;
  private final CourseRepository courseRepository;
  private final EnrollmentRepository enrollmentRepository;
  private final JwtAuthenticationProvider jwtAuthenticationProvider;

  @Override
  public EnrollmentDTO enrollStudent(Long courseId, String tokenJwt) {
    Long userId = jwtAuthenticationProvider.getUserId(tokenJwt);

    User student =
        userRepository
            .findById(userId)
            .orElseThrow(() -> new IllegalArgumentException("User not found"));

    Course course =
        courseRepository
            .findById(courseId)
            .orElseThrow(() -> new IllegalArgumentException("Course not found"));

    var enrollment = new Enrollment(student, course);

    enrollmentRepository.save(enrollment);

    return new EnrollmentDTO(enrollment);
  }
}
