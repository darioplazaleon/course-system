package com.example.coursesystem.dto.lesson;

import com.example.coursesystem.entity.Lesson;

public record LessonDTO(
        Long id,
        String title,
        String videoUrl,
        Long moduleId
) {
    public LessonDTO (Lesson lesson) {
        this(lesson.getId(), lesson.getTitle(), lesson.getVideoUrl(), lesson.getModule().getId());
    }
}
