package com.example.coursesystem.dto.course;

import com.example.coursesystem.entity.Course;

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
