package com.example.coursesystem.dto.course;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.List;

public record CourseAddDTO(
        @NotBlank
        String title,
        @NotBlank
        String description,
        @NotBlank
        BigDecimal price,
        @NotNull
        List<Long> categoryIds,
        @NotNull
        List<Long> languageIds
) {
}
