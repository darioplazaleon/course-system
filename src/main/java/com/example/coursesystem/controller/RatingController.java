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
        String token = jwtToken.split(" ")[1];
        var rating = ratingService.rateCourse(ratingAddDTO, courseId, token);
        return ResponseEntity.ok(rating);
    }
}
