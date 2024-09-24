package com.example.coursesystem.service.impl;

import com.example.coursesystem.dto.CourseAddDTO;
import com.example.coursesystem.dto.CourseDTO;
import com.example.coursesystem.entity.Course;
import com.example.coursesystem.entity.User;
import com.example.coursesystem.repository.CourseRepository;
import com.example.coursesystem.repository.UserRepository;
import com.example.coursesystem.service.CourseService;
import jakarta.persistence.EntityExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ICourseService implements CourseService {

    private final CourseRepository courseRepository;
    private final UserRepository userRepository;

    @Override
    public CourseDTO createCourse(CourseAddDTO courseAddDTO) {
        if (courseRepository.existsByTitle(courseAddDTO.title())) {
            throw new EntityExistsException("Course with title " + courseAddDTO.title() + " already exists");
        }

        User instructor = userRepository.findById(courseAddDTO.instructorId())
                .orElseThrow(() -> new IllegalArgumentException("Instructor with id " + courseAddDTO.instructorId() + " not found"));

        var course = new Course(courseAddDTO, instructor);

        courseRepository.save(course);
        return new CourseDTO(course);
    }
}
