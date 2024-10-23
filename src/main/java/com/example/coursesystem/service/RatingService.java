package com.example.coursesystem.service;

import com.example.coursesystem.dto.rating.RatingAddDTO;
import com.example.coursesystem.dto.rating.RatingDTO;

public interface RatingService {
    RatingDTO rateCourse(RatingAddDTO ratingAddDTO, Long courseId , String jwtToken);
    RatingDTO updateRating(RatingAddDTO ratingAddDTO, Long ratingId);
}
