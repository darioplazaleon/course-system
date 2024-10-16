package com.example.coursesystem.dto.course;

import com.example.coursesystem.dto.module.ModuleDTO;
import com.example.coursesystem.entity.Category;
import com.example.coursesystem.entity.Course;
import com.example.coursesystem.entity.Language;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public record CourseDTO(
    Long id,
    String title,
    String description,
    BigDecimal price,
    String instructorName,
    List<ModuleDTO> modules,
    List<String> categories,
    List<String> languages) {
  public CourseDTO(Course course) {
    this(
        course.getId(),
        course.getTitle(),
        course.getDescription(),
        course.getPrice(),
        course.getInstructor().getName(),
        course.getModules().stream().map(ModuleDTO::new).collect(Collectors.toList()),
        course.getCategories().stream().map(Category::getName).toList(),
        course.getLanguages().stream().map(Language::getName).toList());
  }
}
