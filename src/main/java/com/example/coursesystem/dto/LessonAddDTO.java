package com.example.coursesystem.dto;

public record LessonAddDTO(
        String title,
        String videoUrl,
        Long moduleId
) {
}
