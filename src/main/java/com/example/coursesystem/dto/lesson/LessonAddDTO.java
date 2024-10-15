package com.example.coursesystem.dto.lesson;

import org.springframework.web.multipart.MultipartFile;

public record LessonAddDTO(
        String title,
        Long moduleId,
        MultipartFile video
) {
}
