package com.example.coursesystem.controller;

import com.example.coursesystem.dto.rating.RatingAddDTO;
import com.example.coursesystem.dto.rating.RatingDTO;
import com.example.coursesystem.service.impl.IRatingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/ratings")
public class RatingController {
    private final IRatingService ratingService;

    @PostMapping("/rate/{courseId}")
    public ResponseEntity<RatingDTO> createRating(
            @RequestHeader("Authorization") String jwtToken,
            @PathVariable Long courseId,
            @RequestBody RatingAddDTO ratingAddDTO
    ) {
        var rating = ratingService.rateCourse(ratingAddDTO, courseId, jwtToken);
        return ResponseEntity.ok(rating);
    }

    @PutMapping("/update/{ratingId}")
    public ResponseEntity<RatingDTO> updateRating(
            @PathVariable Long ratingId,
            @RequestBody RatingAddDTO ratingAddDTO
    ) {
        var rating = ratingService.updateRating(ratingAddDTO, ratingId);
        return ResponseEntity.ok(rating);
    }
}
