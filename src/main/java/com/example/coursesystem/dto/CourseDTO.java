package com.example.coursesystem.dto;

import com.example.coursesystem.entity.Course;

import java.util.List;

public record CourseDTO(
        Long id,
        String title,
        String description,
        String instructorName
) {
    public CourseDTO(Course course) {
        this(course.getId(), course.getTitle(), course.getDescription(), course.getInstructor().toString());
    }
}
