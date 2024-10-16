package com.example.coursesystem.dto.module;

import com.example.coursesystem.dto.lesson.LessonDTO;
import com.example.coursesystem.entity.Lesson;
import com.example.coursesystem.entity.Module;

import java.util.List;
import java.util.stream.Collectors;

public record ModuleDTO(
        Long id,
        String title,
        Long courseId,
        List<LessonDTO> lessons
) {
    public ModuleDTO(Module module) {
        this(module.getId(), module.getTitle(), module.getCourse().getId(), module.getLessons().stream().map(LessonDTO::new).collect(Collectors.toList()));
    }
}
