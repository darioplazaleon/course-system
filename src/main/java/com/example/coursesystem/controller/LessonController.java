package com.example.coursesystem.controller;

import com.example.coursesystem.dto.lesson.LessonAddDTO;
import com.example.coursesystem.dto.lesson.LessonDTO;
import com.example.coursesystem.service.impl.ILessonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RequiredArgsConstructor
@RestController
@RequestMapping("/lessons")
public class LessonController {
    private final ILessonService lessonService;

    @PostMapping("/create")
    public ResponseEntity<LessonDTO> createLesson(@ModelAttribute LessonAddDTO lessonAddDTO) throws IOException {
        var lesson = lessonService.createLesson(lessonAddDTO);
        return ResponseEntity.ok(lesson);
    }
}
