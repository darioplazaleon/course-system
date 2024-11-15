package com.example.coursesystem.controller;

import com.example.coursesystem.dto.course.CourseDTO;
import com.example.coursesystem.dto.enrollment.EnrollmentDTO;
import com.example.coursesystem.service.impl.IEnrollmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/enrollments")
public class EnrollmentController {

    private final IEnrollmentService enrollmentService;

    @PostMapping("/enroll/{courseId}")
    public ResponseEntity<EnrollmentDTO> enrollStudent(@PathVariable Long courseId, @RequestHeader("Authorization") String tokenJwt) {
        return ResponseEntity.ok(enrollmentService.enrollStudent(courseId, tokenJwt));
    }

    @GetMapping("/my-enrollments")
    public ResponseEntity<List<CourseDTO>> getMyEnrollments(@RequestHeader("Authorization") String tokenJwt) {
        return ResponseEntity.ok(enrollmentService.getPurchasedCourses(tokenJwt));
    }
}
