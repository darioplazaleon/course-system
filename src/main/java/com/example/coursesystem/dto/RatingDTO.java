package com.example.coursesystem.dto;

import java.time.LocalDateTime;

public record RatingDTO(
        Long id,
        int score,
        String comment,
        String studentName,
        LocalDateTime ratingDate
) {
}
