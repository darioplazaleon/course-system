package com.example.coursesystem.controller;

import com.azure.core.annotation.Post;
import com.example.coursesystem.dto.module.ModuleAddDTO;
import com.example.coursesystem.dto.module.ModuleDTO;
import com.example.coursesystem.dto.module.ModuleWithLessonsDTO;
import com.example.coursesystem.service.impl.IModuleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/modules")
public class ModuleController {

    private final IModuleService moduleService;

    @PostMapping("/create")
    public ResponseEntity<ModuleDTO> createModule(@RequestBody ModuleAddDTO moduleAddDTO) {
        var module = moduleService.createModule(moduleAddDTO);
        return ResponseEntity.ok(module);
    }

    @PostMapping("/create-with-lessons/{courseId}")
    public ResponseEntity<ModuleDTO> createModuleWithLessons(@RequestBody List<ModuleWithLessonsDTO> moduleWithLessonsDTO, @PathVariable Long courseId) {
        for (ModuleWithLessonsDTO moduleWithLessons : moduleWithLessonsDTO) {
            moduleService.createModuleWithLessons(moduleWithLessons, courseId);
        }
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
