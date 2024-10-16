package com.example.coursesystem.repository;

import aj.org.objectweb.asm.commons.Remapper;
import com.example.coursesystem.entity.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    boolean existsByTitle(String title);
    Page<Course> findAllByInstructorId(Long instructorId, Pageable pageable);
}
