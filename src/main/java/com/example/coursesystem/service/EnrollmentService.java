package com.example.coursesystem.service;

import com.example.coursesystem.dto.course.CourseDTO;
import com.example.coursesystem.dto.enrollment.EnrollmentDTO;

import java.util.List;

public interface EnrollmentService {
    EnrollmentDTO enrollStudent(Long courseId, String tokenJwt);
    List<CourseDTO> getPurchasedCourses(String tokenJwt);
}
