package com.example.coursesystem.dto.category;

import jakarta.validation.constraints.NotBlank;

public record CategoryAddDTO(
        @NotBlank
    String name
) {}
