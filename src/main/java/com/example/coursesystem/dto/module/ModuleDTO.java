package com.example.coursesystem.dto.module;

import com.example.coursesystem.entity.Module;

public record ModuleDTO(
    Long id,
    String title,
    Long courseId
) {
    public ModuleDTO(Module module) {
        this(module.getId(), module.getTitle(), module.getCourse().getId());
    }
}
