package com.example.coursesystem.dto.course;

import com.example.coursesystem.dto.module.ModuleDTO;
import com.example.coursesystem.dto.rating.RatingDTO;
import com.example.coursesystem.entity.Category;
import com.example.coursesystem.entity.Course;
import com.example.coursesystem.entity.Language;
import com.example.coursesystem.entity.Rating;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public record CourseDTO(
    Long id,
    String title,
    String description,
    double averageRating,
    BigDecimal price,
    List<RatingDTO> ratings,
    String instructorName,
    List<ModuleDTO> modules,
    List<String> categories,
    List<String> languages) {
  public CourseDTO(Course course) {
    this(
        course.getId(),
        course.getTitle(),
        course.getDescription(),
        course.getAverageRating(),
        course.getPrice(),
        Optional.ofNullable(course.getRatings()).orElse(List.of()).stream()
            .map(RatingDTO::new)
            .collect(Collectors.toList()),
        course.getInstructor().getName(),
        Optional.ofNullable(course.getModules()).orElse(List.of()).stream()
            .map(ModuleDTO::new)
            .collect(Collectors.toList()),
        course.getCategories().stream().map(Category::getName).toList(),
        course.getLanguages().stream().map(Language::getName).toList());
  }
}
