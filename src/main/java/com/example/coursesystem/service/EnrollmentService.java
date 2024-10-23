package com.example.coursesystem.service;

import com.example.coursesystem.dto.enrollment.EnrollmentDTO;

public interface EnrollmentService {
    EnrollmentDTO enrollStudent(Long courseId, String tokenJwt);

}
