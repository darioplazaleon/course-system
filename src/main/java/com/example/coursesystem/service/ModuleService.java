package com.example.coursesystem.service;

import com.example.coursesystem.dto.module.ModuleAddDTO;
import com.example.coursesystem.dto.module.ModuleDTO;
import com.example.coursesystem.dto.module.ModuleWithLessonsDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ModuleService {
    ModuleDTO createModule(ModuleAddDTO moduleAddDTO);
    ModuleDTO createModuleWithLessons(ModuleWithLessonsDTO moduleWithLessonsDTO, Long courseId);
    List<ModuleDTO> getCourseModules(Long courseId);
    void deleteModuleById(Long id);
    ModuleDTO updateModule(Long id, ModuleDTO moduleDTO);
}
