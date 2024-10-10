package com.example.coursesystem.dto;

public record LessonDTO(
        Long id,
        String title,
        String videoUrl,
        Long moduleId
) {
    public LessonDTO {

    }
}
