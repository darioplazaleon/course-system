package com.example.coursesystem.dto.enrollment;

import com.example.coursesystem.entity.Enrollment;

import java.time.LocalDateTime;

public record EnrollmentDTO(
    Long id, String studentName, String courseTitle, LocalDateTime enrollmentDate, String status) {
  public EnrollmentDTO(Enrollment enrollment) {
    this(
        enrollment.getId(),
        enrollment.getStudent().getName(),
        enrollment.getCourse().getTitle(),
        enrollment.getEnrolledDate(),
        enrollment.getStatus().name());
  }
}
