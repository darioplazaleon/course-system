package com.example.coursesystem.controller;

import com.example.coursesystem.dto.category.CategoryAddDTO;
import com.example.coursesystem.dto.category.CategoryDTO;
import com.example.coursesystem.service.impl.ICategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/categories")
public class CategoryController {
  public final ICategoryService categoryService;

  @PostMapping("/create")
  public ResponseEntity<CategoryDTO> createCategory(@RequestBody CategoryAddDTO categoryAddDTO) {
    var category = categoryService.createCategory(categoryAddDTO);
    return ResponseEntity.ok(category);
  }

  @GetMapping("/all")
  public ResponseEntity<List<CategoryDTO>> getAllCategories() {
    return ResponseEntity.ok(categoryService.getAllCategories());
  }

  @GetMapping("/{id}")
  public ResponseEntity<CategoryDTO> getCategoryById(@PathVariable Long id) {
    return ResponseEntity.ok(categoryService.getCategoryById(id));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteCategoryById(@PathVariable Long id) {
    categoryService.deleteCategory(id);
    return ResponseEntity.noContent().build();
  }

  @PutMapping("/{id}")
  public ResponseEntity<CategoryDTO> updateCategory(
      @PathVariable Long id, @RequestBody CategoryAddDTO categoryAddDTO) {
    var category = categoryService.updateCategory(id, categoryAddDTO);
    return ResponseEntity.ok(category);
  }
}
