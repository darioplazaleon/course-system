package com.example.coursesystem.service.impl;

import com.example.coursesystem.dto.ModuleAddDTO;
import com.example.coursesystem.dto.ModuleDTO;
import com.example.coursesystem.entity.Module;
import com.example.coursesystem.repository.CourseRepository;
import com.example.coursesystem.repository.ModuleRepository;
import com.example.coursesystem.service.ModuleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class IModuleService implements ModuleService {

    private final ModuleRepository moduleRepository;
    private final CourseRepository courseRepository;

    @Override
    public ModuleDTO createModule(ModuleAddDTO moduleAddDTO) {
        if(moduleRepository.existsByTitle(moduleAddDTO.title())) {
            throw new IllegalArgumentException("Module with title " + moduleAddDTO.title() + " already exists");
        }

        var course = courseRepository.findById(moduleAddDTO.courseId())
                .orElseThrow(() -> new IllegalArgumentException("Course with id " + moduleAddDTO.courseId() + " not found"));

        var module = new Module(moduleAddDTO, course);

        moduleRepository.save(module);

        return new ModuleDTO(module);
    }

    @Override
    public Page<ModuleDTO> getCourseModules(Pageable pageable, Long courseId) {
        return null;
    }

    @Override
    public void deleteModuleById(Long id) {
        var foundModule = moduleRepository.findById(id);
        if (foundModule.isEmpty()) {
            throw new IllegalArgumentException("Module with id " + id + " not found");
        } else {
            moduleRepository.deleteById(id);
        }
    }

    @Override
    public ModuleDTO updateModule(Long id, ModuleDTO moduleDTO) {
        var module = moduleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Module with id " + id + " not found"));

        module.updateInfo(moduleDTO);
        moduleRepository.save(module);

        return new ModuleDTO(module);
    }
}
