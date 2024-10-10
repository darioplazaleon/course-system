package com.example.coursesystem.service.impl;

import com.example.coursesystem.dto.LessonAddDTO;
import com.example.coursesystem.dto.LessonDTO;
import com.example.coursesystem.entity.Lesson;
import com.example.coursesystem.entity.Module;
import com.example.coursesystem.repository.LessonRepository;
import com.example.coursesystem.repository.ModuleRepository;
import com.example.coursesystem.service.LessonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RequiredArgsConstructor
@Service
public class ILessonService implements LessonService {

    private final LessonRepository lessonRepository;
    private final ModuleRepository moduleRepository;
    private final AzureBlobService azureBlobService;

    @Override
    public LessonDTO createLesson(LessonAddDTO lessonAddDTO) throws IOException {
        Module module = moduleRepository.findById(lessonAddDTO.moduleId())
                .orElseThrow(() -> new IllegalArgumentException("Module with id " + lessonAddDTO.moduleId() + " not found"));

        String videoUrl = azureBlobService.uploadVideoToAzure(lessonAddDTO.video());

        var lesson = new Lesson(lessonAddDTO, module, videoUrl);

        lessonRepository.save(lesson);

        return new LessonDTO(lesson);
    }

    @Override
    public void deleteLesson(Long lessonId) {
        var foundLesson = lessonRepository.findById(lessonId);
        if (foundLesson.isEmpty()) {
            throw new IllegalArgumentException("Lesson with id " + lessonId + " not found");
        } else {
            lessonRepository.deleteById(lessonId);
        }
    }
}
