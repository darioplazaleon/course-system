package com.example.coursesystem.dto.rating;

import com.example.coursesystem.entity.Rating;

import java.time.LocalDateTime;

public record RatingDTO(
    Long id, int score, String comment, String studentName, LocalDateTime ratingDate) {
  public RatingDTO(Rating rating) {
    this(
        rating.getId(),
        rating.getScore(),
        rating.getComment(),
        rating.getStudent().getName(),
        rating.getRatingDate());
  }
}
