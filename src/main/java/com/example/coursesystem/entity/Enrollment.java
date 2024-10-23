package com.example.coursesystem.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "enrollments")
@Getter
@NoArgsConstructor
public class Enrollment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private User student;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    private LocalDateTime enrolledDate;

    @Enumerated(EnumType.STRING)
    private EnrollmentStatus status;

    public Enrollment(User student, Course course) {
        this.student = student;
        this.course = course;
        this.enrolledDate = LocalDateTime.now();
        this.status = EnrollmentStatus.IN_PROGRESS;
    }
}
