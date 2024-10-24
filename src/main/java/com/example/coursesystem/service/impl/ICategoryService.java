package com.example.coursesystem.service.impl;

import com.example.coursesystem.dto.category.CategoryAddDTO;
import com.example.coursesystem.dto.category.CategoryDTO;
import com.example.coursesystem.entity.Category;
import com.example.coursesystem.repository.CategoryRepository;
import com.example.coursesystem.service.CategoryService;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ICategoryService implements CategoryService {

  private final CategoryRepository categoryRepository;

  @Override
  public CategoryDTO createCategory(CategoryAddDTO categoryAddDTO) {

    System.out.println("categoryAddDTO: " + categoryAddDTO);

    if (categoryRepository.existsByName(categoryAddDTO.name())) {
      throw new EntityExistsException(
          "Category with name: " + categoryAddDTO.name() + " already exists");
    }

    System.out.println("categoryAddDTO: " + categoryAddDTO);

    var category = new Category(categoryAddDTO);

    categoryRepository.save(category);

    return new CategoryDTO(category);
  }

  @Override
  public List<CategoryDTO> getAllCategories() {
    return categoryRepository.findAll().stream().map(CategoryDTO::new).toList();
  }

  @Override
  public CategoryDTO getCategoryById(Long id) {
    return categoryRepository.findById(id).map(CategoryDTO::new).orElse(null);
  }

  @Override
  public CategoryDTO updateCategory(Long id, CategoryAddDTO categoryAddDTO) {
    var category = categoryRepository.findById(id).orElse(null);

    if (category == null) {
      throw new EntityNotFoundException("Category with id: " + id + " not found");
    }

    category.updateCategory(categoryAddDTO);

    categoryRepository.save(category);

    return new CategoryDTO(category);
  }

  @Override
  public void deleteCategory(Long id) {
    var category = categoryRepository.findById(id).orElse(null);

    if (category == null) {
      throw new EntityNotFoundException("Category with id: " + id + " not found");
    }

    categoryRepository.delete(category);
  }
}
