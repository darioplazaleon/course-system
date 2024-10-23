package com.example.coursesystem.service.impl;

import com.example.coursesystem.dto.rating.RatingAddDTO;
import com.example.coursesystem.dto.rating.RatingDTO;
import com.example.coursesystem.entity.Course;
import com.example.coursesystem.entity.Rating;
import com.example.coursesystem.entity.User;
import com.example.coursesystem.repository.CourseRepository;
import com.example.coursesystem.repository.RatingRepository;
import com.example.coursesystem.repository.UserRepository;
import com.example.coursesystem.security.JwtAuthenticationProvider;
import com.example.coursesystem.service.RatingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class IRatingService implements RatingService {

    private final RatingRepository ratingRepository;
    private final UserRepository userRepository;
    private final CourseRepository courseRepository;
    private final JwtAuthenticationProvider jwtAuthenticationProvider;

    @Override
    @Transactional
    public RatingDTO rateCourse(RatingAddDTO ratingAddDTO, Long courseId, String jwtToken) {
        Long userId = jwtAuthenticationProvider.getUserId(jwtToken);

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new IllegalArgumentException("Course not found"));

        var rating = new Rating(ratingAddDTO, course, user);

        ratingRepository.save(rating);

        return new RatingDTO(rating);
    }

    @Override
    public RatingDTO updateRating(RatingAddDTO ratingAddDTO, Long ratingId) {
        var foundRating = ratingRepository.findById(ratingId)
                .orElseThrow(() -> new IllegalArgumentException("Rating not found"));

        foundRating.updateRating(ratingAddDTO);

        ratingRepository.save(foundRating);

        return new RatingDTO(foundRating);
    }
}
