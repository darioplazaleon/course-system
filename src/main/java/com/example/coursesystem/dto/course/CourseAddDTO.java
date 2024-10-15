package com.example.coursesystem.dto.course;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CourseAddDTO(
        @NotBlank
        String title,
        @NotBlank
        String description
) {
}
