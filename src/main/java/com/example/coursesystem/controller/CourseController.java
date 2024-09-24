package com.example.coursesystem.controller;

import com.example.coursesystem.dto.CourseAddDTO;
import com.example.coursesystem.dto.CourseDTO;
import com.example.coursesystem.service.impl.ICourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/courses")
public class CourseController {

    private final ICourseService courseService;

    @PostMapping("/create")
    public ResponseEntity<CourseDTO> createCourse(@RequestBody CourseAddDTO courseAddDTO) {
        var course = courseService.createCourse(courseAddDTO);
        return ResponseEntity.ok(course);
    }

}
