package com.example.coursesystem.service;

import com.example.coursesystem.dto.CourseAddDTO;
import com.example.coursesystem.dto.CourseDTO;

public interface CourseService {
    CourseDTO createCourse(CourseAddDTO courseAddDTO);
}
