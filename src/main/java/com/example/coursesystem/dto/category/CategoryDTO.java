package com.example.coursesystem.dto.category;

import com.example.coursesystem.entity.Category;

public record CategoryDTO(
    Long id,
    String name
) {
    public CategoryDTO(Category category) {
        this(category.getId(), category.getName());
    }
}
