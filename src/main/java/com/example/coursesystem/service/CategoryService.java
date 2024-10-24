package com.example.coursesystem.service;

import com.example.coursesystem.dto.category.CategoryAddDTO;
import com.example.coursesystem.dto.category.CategoryDTO;

import java.util.List;

public interface CategoryService {
    CategoryDTO createCategory(CategoryAddDTO categoryAddDTO);
    List<CategoryDTO> getAllCategories();
    CategoryDTO getCategoryById(Long id);
    CategoryDTO updateCategory(Long id, CategoryAddDTO categoryAddDTO);
    void deleteCategory(Long id);
}
