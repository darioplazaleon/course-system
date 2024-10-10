package com.example.coursesystem.service;

import com.example.coursesystem.dto.LessonAddDTO;
import com.example.coursesystem.dto.LessonDTO;

public interface LessonService {
    LessonDTO createLesson(LessonAddDTO lessonAddDTO);
    void deleteLesson(Long lessonId);
}
