package com.example.coursesystem.dto.module;

import com.example.coursesystem.dto.lesson.LessonDTO;
import com.example.coursesystem.dto.lesson.LessonModuleDTO;

import java.util.List;

public record ModuleWithLessonsDTO(
        String title,
        List<LessonModuleDTO> lessons
) {
}
