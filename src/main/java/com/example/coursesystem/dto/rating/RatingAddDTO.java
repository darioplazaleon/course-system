package com.example.coursesystem.dto.rating;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record RatingAddDTO(@Min(1) @Max(5) int score, @NotNull String comment) {}
