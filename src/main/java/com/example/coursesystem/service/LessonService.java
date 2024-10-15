package com.example.coursesystem.service;

import com.example.coursesystem.dto.lesson.LessonAddDTO;
import com.example.coursesystem.dto.lesson.LessonDTO;

import java.io.IOException;

public interface LessonService {

    LessonDTO createLesson(LessonAddDTO lessonAddDTO) throws IOException;

    void deleteLesson(Long lessonId);
}
