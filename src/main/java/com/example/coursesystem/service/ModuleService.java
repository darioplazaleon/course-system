package com.example.coursesystem.service;

import com.example.coursesystem.dto.ModuleDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ModuleService {
    ModuleDTO createModule(ModuleDTO moduleDTO);
    Page<ModuleDTO> getCourseModules(Pageable pageable, Long courseId);
    void deleteModuleById(Long id);
    ModuleDTO updateModule(Long id, ModuleDTO moduleDTO);
}
