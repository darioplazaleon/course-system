package com.example.coursesystem.service.impl;

import com.example.coursesystem.dto.course.CourseAddDTO;
import com.example.coursesystem.dto.course.CourseDTO;
import com.example.coursesystem.entity.Course;
import com.example.coursesystem.entity.User;
import com.example.coursesystem.repository.CourseRepository;
import com.example.coursesystem.repository.UserRepository;
import com.example.coursesystem.security.JwtAuthenticationProvider;
import com.example.coursesystem.service.CourseService;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ICourseService implements CourseService {

    private final CourseRepository courseRepository;
    private final UserRepository userRepository;
    private final JwtAuthenticationProvider jwtAuthenticationProvider;

    @Override
    public CourseDTO createCourse(CourseAddDTO courseAddDTO, String token) {
        if (courseRepository.existsByTitle(courseAddDTO.title())) {
            throw new EntityExistsException("Course with title " + courseAddDTO.title() + " already exists");
        }

        Long userId = jwtAuthenticationProvider.getUserId(token);

        User instructor = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Instructor with id " + userId + " not found"));

        var course = new Course(courseAddDTO, instructor);

        courseRepository.save(course);
        return new CourseDTO(course);
    }

    @Override
    public Page<CourseDTO> getAllCourses(Pageable pageable) {
        return courseRepository.findAll(pageable).map(CourseDTO::new);
    }

    @Override
    public Page<CourseDTO> getAllCoursesByUserId(Pageable pageable, String token) {
        Long userId = jwtAuthenticationProvider.getUserId(token);
        return courseRepository.findAllByInstructorId(userId, pageable).map(CourseDTO::new);
    }

    @Override
    public CourseDTO getCourseById(Long id) {
        return courseRepository.findById(id)
                .map(CourseDTO::new)
                .orElseThrow(() -> new IllegalArgumentException("Course with id " + id + " not found"));
    }

    @Override
    public void deleteCourseById(Long id) {
        var foundCourse = courseRepository.findById(id);
        if (foundCourse.isEmpty()) {
            throw new EntityNotFoundException("Course with id " + id + " not found");
        } else {
            courseRepository.deleteById(id);
        }
    }

    @Override
    public CourseDTO updateCourse(Long id, CourseAddDTO courseAddDTO) {
        var foundCourse = courseRepository.findById(id);

        if (foundCourse.isEmpty()) {
            throw new EntityNotFoundException("Course with id " + id + " not found");
        }

        var course = foundCourse.get();

        course.updateInfo(courseAddDTO);
        courseRepository.save(course);

        return new CourseDTO(course);
    }
}
