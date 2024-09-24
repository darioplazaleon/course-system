package com.example.coursesystem.service;

import com.example.coursesystem.dto.CourseAddDTO;
import com.example.coursesystem.dto.CourseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CourseService {
    CourseDTO createCourse(CourseAddDTO courseAddDTO);
    Page<CourseDTO> getAllCourses(Pageable pageable);
    CourseDTO getCourseById(Long id);
    void deleteCourseById(Long id);
    CourseDTO updateCourse(Long id, CourseAddDTO courseAddDTO);
}
