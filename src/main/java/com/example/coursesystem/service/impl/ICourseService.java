package com.example.coursesystem.service.impl;

import com.example.coursesystem.dto.CourseAddDTO;
import com.example.coursesystem.dto.CourseDTO;
import com.example.coursesystem.entity.Course;
import com.example.coursesystem.repository.CourseRepository;
import com.example.coursesystem.service.CourseService;
import jakarta.persistence.EntityExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ICourseService implements CourseService {

    private final CourseRepository courseRepository;

    public ICourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public CourseDTO createCourse(CourseAddDTO courseAddDTO) {
        if(courseRepository.existsByTitle(courseAddDTO.title())) {
            throw new EntityExistsException("Course with title " + courseAddDTO.title() + " already exists");
        }

        var course = new Course(courseAddDTO);
        courseRepository.save(course);
        return new CourseDTO(course);
    }
}
