package com.example.coursesystem.repository;

import com.example.coursesystem.entity.Course;
import com.example.coursesystem.entity.Rating;
import com.example.coursesystem.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Long> {
    Rating findByStudentAndCourseId(User user, Course course);
}
