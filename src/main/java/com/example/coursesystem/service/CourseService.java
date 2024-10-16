package com.example.coursesystem.service;

import com.example.coursesystem.dto.course.CourseAddDTO;
import com.example.coursesystem.dto.course.CourseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CourseService {
    CourseDTO createCourse(CourseAddDTO courseAddDTO, String token);
    Page<CourseDTO> getAllCourses(Pageable pageable);
    Page<CourseDTO> getAllCoursesByUserId(Pageable pageable, String token);
    CourseDTO getCourseById(Long id);
    void deleteCourseById(Long id);
    CourseDTO updateCourse(Long id, CourseAddDTO courseAddDTO);
}
