package com.example.coursesystem.controller;

import com.example.coursesystem.dto.CourseAddDTO;
import com.example.coursesystem.dto.CourseDTO;
import com.example.coursesystem.service.impl.ICourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    @GetMapping("/all")
    public ResponseEntity<Page<CourseDTO>> getAllCourses(Pageable pageable) {
        return ResponseEntity.ok(courseService.getAllCourses(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseDTO> getCourseById(@PathVariable Long id) {
        return ResponseEntity.ok(courseService.getCourseById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourseById(@PathVariable Long id) {
        courseService.deleteCourseById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<CourseDTO> updateCourse(@PathVariable Long id, @RequestBody CourseAddDTO courseAddDTO) {
        var course = courseService.updateCourse(id, courseAddDTO);
        return ResponseEntity.ok(course);
    }
}
