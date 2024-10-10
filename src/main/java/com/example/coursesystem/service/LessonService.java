package com.example.coursesystem.service;

import com.example.coursesystem.dto.LessonAddDTO;
import com.example.coursesystem.dto.LessonDTO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface LessonService {

    LessonDTO createLesson(LessonAddDTO lessonAddDTO) throws IOException;

    void deleteLesson(Long lessonId);
}
