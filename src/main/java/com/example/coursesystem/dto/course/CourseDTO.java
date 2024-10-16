package com.example.coursesystem.dto.course;

import com.example.coursesystem.dto.module.ModuleDTO;
import com.example.coursesystem.entity.Course;
import com.example.coursesystem.entity.Module;

import java.util.List;
import java.util.stream.Collectors;

public record CourseDTO(
        Long id,
        String title,
        String description,
        String instructorName,
        List<ModuleDTO> modules
) {
    public CourseDTO(Course course) {
        this(course.getId(), course.getTitle(), course.getDescription(), course.getInstructor().getName(), course.getModules().stream().map(ModuleDTO::new).collect(Collectors.toList()));
    }
}
