package com.example.coursesystem.entity;

import com.example.coursesystem.dto.rating.RatingAddDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "ratings")
@Getter
@NoArgsConstructor
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int score;
    private String comment;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private User student;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    private LocalDateTime ratingDate;

    public Rating(RatingAddDTO ratingAddDTO, Course course, User student) {
        this.score = ratingAddDTO.score();
        this.comment = ratingAddDTO.comment();
        this.ratingDate = LocalDateTime.now();
        this.student = student;
        this.course = course;
    }

    public void updateRating(RatingAddDTO ratingAddDTO) {
        this.score = ratingAddDTO.score();
        this.comment = ratingAddDTO.comment();
    }
}
